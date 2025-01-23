package com.mocicarazvan.dwoltp.services.common;

import org.springframework.data.domain.Page;

import java.util.List;

public interface GetModel<T, ID> {

    T getModelById(ID id);

    Page<T> getModelsPage(int page, int size, String sortField, Boolean ascending);

    List<T> getModelsByIds(List<ID> ids);

}
