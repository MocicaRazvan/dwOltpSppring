package com.mocicarazvan.dwoltp.repositories;

import com.mocicarazvan.dwoltp.enums.PlataTip;
import com.mocicarazvan.dwoltp.models.Comanda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ComandaRepository extends JpaRepository<Comanda, Long> {


    @Query("""
                                select c from Comanda c
                                where
                                    (:minSuma is null or c.suma >= :minSuma)
                                    and (:maxSuma is null or c.suma <= :maxSuma)
                                    and (:minDataOnorare is null or c.dataOnorare >= :minDataOnorare)
                                    and (:maxDataOnorare is null or c.dataOnorare <= :maxDataOnorare)
                                    and (:clientId = -1 or c.client.id = :clientId)
                                    and (:soferId = -1 or c.sofer.id = :soferId)
                                    and (:nrPlati = -1 or (select count(p) from c.plati p) = :nrPlati)
                                    and (:locatieId = -1 or c.locatie.id = :locatieId)
                                    and (:produseIds is null or exists (select 1 from c.comandaProduses where exists (select 1 from ComandaProdus cp where cp.comanda.id = c.id and cp.produs.id in :produseIds)))
                                    and (:tipPlati is null or exists (select 1 from c.plati p where p.tip in :tipPlati))
            """)
    Page<Comanda> findAllByCustom(Double minSuma, Double maxSuma,
                                  LocalDate minDataOnorare, LocalDate maxDataOnorare,
                                  Long clientId, Long soferId, Long nrPlati,
                                  Long locatieId, List<Long> produseIds,
                                  List<PlataTip> tipPlati, Pageable pageable);

}