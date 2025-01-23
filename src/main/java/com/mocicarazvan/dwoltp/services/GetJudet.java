package com.mocicarazvan.dwoltp.services;


import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import com.mocicarazvan.dwoltp.models.Judet;
import com.mocicarazvan.dwoltp.repositories.JudetRepository;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import com.mocicarazvan.dwoltp.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetJudet implements GetModel<Judet, Long> {
    private final JudetRepository repository;

    @Override
    public Judet getModelById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("judet", "id")
        );
    }

    @Override
    public Page<Judet> getModelsPage(int page, int size, String sortField, Boolean ascending) {
        return repository.findAll(PageableUtils.createPageRequest(page, size, sortField, ascending));
    }

    @Override
    public List<Judet> getModelsByIds(List<Long> longs) {
        return repository.findAllById(longs);
    }
}
