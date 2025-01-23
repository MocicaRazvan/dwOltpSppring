package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.dtos.body.ComandaBody;
import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.models.*;
import com.mocicarazvan.dwoltp.repositories.ComandaRepository;
import com.mocicarazvan.dwoltp.services.common.BaseService;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import com.mocicarazvan.dwoltp.utils.WrapNotFoundErrorFuture;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class ComandaService extends BaseService<Long, Comanda, ComandaBody, Comanda, ComandaRepository> {
    public ComandaService(ComandaRepository repository, DtosModelMapper<ComandaBody, Comanda, Comanda> mapper, GetModel<Client, Long> dependencyClientGetter, GetModel<Locatie, Long> dependencyLocatieGetter, GetModel<Produs, Long> dependencyProdusGetter, GetModel<SoferLivrari, Long> dependencySoferLivrariGetter) {
        super(repository, mapper, "comanda");
        this.dependencyClientGetter = dependencyClientGetter;
        this.dependencyLocatieGetter = dependencyLocatieGetter;
        this.dependencyProdusGetter = dependencyProdusGetter;
        this.dependencySoferLivrariGetter = dependencySoferLivrariGetter;
    }

    private final GetModel<Client, Long> dependencyClientGetter;
    private final GetModel<Locatie, Long> dependencyLocatieGetter;
    private final GetModel<Produs, Long> dependencyProdusGetter;
    private final GetModel<SoferLivrari, Long> dependencySoferLivrariGetter;
    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();

    @Override
    public Comanda saveBody(ComandaBody comandaBody) {
        Comanda comanda = mapper.fromBodyToModel(comandaBody);
        HashMap<Long, Short> produseCantitate = comandaBody.getComandaProduse().stream().collect(HashMap::new,
                (map, cp) -> map.put(cp.getProdusId(), cp.getCantitate())
                , HashMap::putAll);
        CompletableFuture<List<Produs>> produse = CompletableFuture.supplyAsync(() -> dependencyProdusGetter.getModelsByIds(List.copyOf(produseCantitate.keySet())), executor);
        CompletableFuture<Client> client = CompletableFuture.supplyAsync(() -> dependencyClientGetter.getModelById(comandaBody.getClientId()), executor);
        CompletableFuture<Locatie> locatie = CompletableFuture.supplyAsync(() -> dependencyLocatieGetter.getModelById(comandaBody.getLocatieId()), executor);
        CompletableFuture<SoferLivrari> soferLivrari = CompletableFuture.supplyAsync(() -> dependencySoferLivrariGetter.getModelById(comandaBody.getSoferId()), executor);

        return WrapNotFoundErrorFuture.wrapCallable(() -> saveComanda(comanda, client, locatie, produse, produseCantitate, soferLivrari), serviceName);

    }

    private Comanda saveComanda(Comanda comanda, CompletableFuture<Client> client, CompletableFuture<Locatie> locatie, CompletableFuture<List<Produs>> produse, HashMap<Long, Short> produseCantitate, CompletableFuture<SoferLivrari> soferLivrari) {
        comanda.setClient(client.join());
        comanda.setLocatie(locatie.join());
        comanda.setComandaProduses(produse.join().stream().map(p -> new ComandaProdus(comanda, p, produseCantitate.get(p.getId()))).toList());
        comanda.setSofer(soferLivrari.join());
        return mapper.fromModelToResponse(
                repository.save(comanda)
        );
    }

    public Page<Comanda> getPageable(int page, int size, String sortField, boolean ascending, Double minSuma, Double maxSuma,
                                     LocalDate minDataOnorare, LocalDate maxDataOnorare,
                                     Long clientId, Long soferId, Long nrPlati,
                                     Long locatieId, List<Long> produseIds,
                                     List<PlataTip> tipPlati) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(minSuma, maxSuma, minDataOnorare, maxDataOnorare, clientId, soferId, nrPlati, locatieId, produseIds, tipPlati, pr));
    }
}
