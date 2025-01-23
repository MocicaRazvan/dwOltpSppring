package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import com.mocicarazvan.dwoltp.models.Zona;
import com.mocicarazvan.dwoltp.repositories.ZonaRepository;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import com.mocicarazvan.dwoltp.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetZona implements GetModel<Zona, Long> {
    private final ZonaRepository repository;

    @Override
    public Zona getModelById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("zona", "id")
        );
    }

    @Override
    public Page<Zona> getModelsPage(int page, int size, String sortField, Boolean ascending) {
        return repository.findAll(PageableUtils.createPageRequest(page, size, sortField, ascending));

    }

    @Override
    public List<Zona> getModelsByIds(List<Long> longs) {
        return repository.findAllById(longs);
    }
}
