//package com.mocicarazvan.dwoltp.services;
//
//
//import com.mocicarazvan.dwoltp.dtos.zona.ZonaBody;
//import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
//import com.mocicarazvan.dwoltp.models.Zona;
//import com.mocicarazvan.dwoltp.repositories.ZonaRepository;
//import com.mocicarazvan.dwoltp.services.common.BaseService;
//import com.mocicarazvan.dwoltp.utils.QueryFunctionsFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
/// / just for test purposes useless use GetZonaById
/// /@Service
/// /public class ZonaService extends BaseService<Long, Zona, ZonaBody, Zona, ZonaRepository> {
/// /    public ZonaService(ZonaRepository repository, DtosModelMapper<ZonaBody, Zona, Zona> mapper) {
/// /        super(repository, mapper, "zona");
/// /    }
/// /
/// /    public Page<Zona> getPageable(int page, int size, String sortField, boolean ascending, String numeQuery, String isoQuery) {
/// /        return getPageable(page, size, sortField, ascending, (pr) ->
/// /                QueryFunctionsFactory.getFunction(List.of(numeQuery, isoQuery),
/// /                        List.of(() -> repository.findAllByNumeContainingIgnoreCase(numeQuery, pr),
/// /                                () -> repository.findAllByIsoContainingIgnoreCase(isoQuery, pr)),
/// /                        () -> repository.findAllByNumeContainingIgnoreCaseOrIsoContainingIgnoreCase(numeQuery, isoQuery, pr)
/// /                ));
/// /    }
/// /}
