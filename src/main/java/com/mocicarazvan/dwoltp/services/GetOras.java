package com.mocicarazvan.dwoltp.services;

import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import com.mocicarazvan.dwoltp.models.Oras;
import com.mocicarazvan.dwoltp.repositories.OrasRepository;
import com.mocicarazvan.dwoltp.services.common.GetModel;
import com.mocicarazvan.dwoltp.utils.PageableUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetOras implements GetModel<Oras, Long> {
    private final OrasRepository repository;

    @Override
    public Oras getModelById(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException("oras", "id")
        );
    }

    @Override
    public Page<Oras> getModelsPage(int page, int size, String sortField, Boolean ascending) {
        return repository.findAll(PageableUtils.createPageRequest(page, size, sortField, ascending));

    }

    @Override
    public List<Oras> getModelsByIds(List<Long> longs) {
        return repository.findAllById(longs);
    }


}
