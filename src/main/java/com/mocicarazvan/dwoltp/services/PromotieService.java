package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.dtos.body.PromotieBody;
import com.mocicarazvan.dwoltp.mappers.PromotieMapper;
import com.mocicarazvan.dwoltp.models.Produs;
import com.mocicarazvan.dwoltp.models.Promotie;
import com.mocicarazvan.dwoltp.repositories.PromotieRepository;
import com.mocicarazvan.dwoltp.services.common.BaseServiceWithDependency;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import org.springframework.data.domain.Page;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class PromotieService extends BaseServiceWithDependency
        <Long, Long, Promotie, PromotieBody, Promotie, PromotieRepository, Produs> {
    public PromotieService(PromotieRepository repository, PromotieMapper mapper, GetModel<Produs, Long> dependencyGetter
    ) {
        super(repository, mapper, "promotie", dependencyGetter);
    }


    @Override
    public Promotie setDependency(PromotieBody angajatBody, Produs dependency) {
        Promotie a = mapper.fromBodyToModel(angajatBody);
        a.setProdus(dependency);
        return a;
    }

    @Override
    public Promotie setDependency(PromotieBody promotieBody, Produs dependency, Long aLong) {
        Promotie c = getModelById(aLong);
        mapper.updateModelFromBody(promotieBody, c);
        c.setProdus(dependency);
        return c;
    }

    public Page<Promotie> getPageable(int page, int size, String sortField, boolean ascending, LocalDate perioadaStart, LocalDate perioadaFinal, Long produsId,
                                      BigDecimal discountMin, BigDecimal discountMax) {
        return getPageable(page, size, sortField, ascending, (pr) -> repository.findAllByCustom(perioadaStart, perioadaFinal, produsId, discountMin, discountMax, pr));
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldUpdate(PromotieBody angajatBody, Long id) {
        return Pair.of(repository.existsAllByPerioadeAndProdAndIdNot(angajatBody.getPerioadaFinal(), angajatBody.getPerioadaStart(), angajatBody.getProdusId(), id), "perioadaFinal, perioadaStart, produsId");
    }

    @Override
    public Pair<Boolean, String> existsByUniqueFieldCreate(PromotieBody angajatBody) {
        return Pair.of(repository.existsAllByPerioadeAndProd(angajatBody.getPerioadaFinal(), angajatBody.getPerioadaStart(), angajatBody.getProdusId()), "perioadaFinal, perioadaStart, produsId");
    }

}
