package com.mocicarazvan.dwoltp.services.common;

import com.mocicarazvan.dwoltp.exceptions.EntityWithUniqueExists;
import com.mocicarazvan.dwoltp.exceptions.NotFoundException;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.utils.GetId;
import com.mocicarazvan.dwoltp.utils.PageableUtils;
import com.mocicarazvan.dwoltp.utils.TransformableWrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
public abstract class BaseService<ID, MODEL extends GetId<ID>, BODY, RESPONSE, REPOSITORY extends JpaRepository<MODEL, ID>> implements GetModel<MODEL, ID> {
    protected final REPOSITORY repository;
    protected final DtosModelMapper<BODY, MODEL, RESPONSE> mapper;
    protected final String serviceName;


    @Override
    public MODEL getModelById(ID id) {
        return repository.findById(id).orElseThrow(
                () -> new NotFoundException(serviceName, "id")
        );
    }

    @Override
    public List<MODEL> getModelsByIds(List<ID> ids) {
        List<MODEL> models = repository.findAllById(ids);
        if (models.size() != ids.size()) {
            throw new NotFoundException(serviceName, "id");
        }
        return models;
    }

    public TransformableWrappers.MappableWrapper<Boolean> existsById(ID id) {
        return TransformableWrappers.of(repository.existsById(id))
                .map(b -> {
                    if (!b) {
                        throw new NotFoundException(serviceName, "id");
                    }
                    return b;
                });
    }

    @Override
    public Page<MODEL> getModelsPage(int page, int size, String sortField, Boolean ascending) {
        return repository.findAll(PageableUtils.createPageRequest(page, size, sortField, ascending));

    }

    public Pair<Boolean, String> existsByUniqueFieldCreate(BODY body) {
        return Pair.of(false, "");
    }

    public Pair<Boolean, String> existsByUniqueFieldUpdate(BODY body, ID id) {
        return Pair.of(false, "");
    }


    public MODEL saveFromBody(BODY body) {
        return TransformableWrappers.of(repository.save(mapper.fromBodyToModel(body)))
                .mapToValue(m -> getModelById(m.getId()));
    }

    public TransformableWrappers.DefaultMappableWrapper verifyExistsByUniqueField(BODY body) {
        return verifyExistsByUniqueField(body, this::existsByUniqueFieldCreate);
    }

    public TransformableWrappers.DefaultMappableWrapper verifyExistsByUniqueField(BODY body, ID id) {
        return verifyExistsByUniqueField(body, body1 -> existsByUniqueFieldUpdate(body1, id));
    }


    public RESPONSE create(BODY body) {
        return
                verifyExistsByUniqueField(body).mapToValue(_ ->
                        saveBody(body)
                );
    }


    public RESPONSE update(ID id, BODY body) {
        return
                existsById(id).mapToValue(_ -> verifyExistsByUniqueField(body, id).mapToValue(_ ->
                        saveBody(body)
                ));
    }


    public RESPONSE getById(ID id) {
        return mapper.fromModelToResponse(repository.findById(id).orElseThrow(
                () -> new NotFoundException(serviceName, "id")
        ));
    }

    public void deleteById(ID id) {
        existsById(id).map(_ -> {
            repository.deleteById(id);
            return null;
        });


    }

    public <R, M> Page<R> getPageable(int page, int size, String sortField, boolean ascending, Function<PageRequest, Page<M>> pageableFunction,
                                      Function<M, R> mapper
    ) {
        return TransformableWrappers.of(pageableFunction.apply(PageableUtils.createPageRequest(page, size, sortField, ascending)))
                .mapToValue(pageMappableWrapper -> new PageImpl<>(pageMappableWrapper.getContent(), pageMappableWrapper.getPageable(), pageMappableWrapper.getTotalElements())
                        .map(mapper)
                );


    }

    public Page<RESPONSE> getPageable(int page, int size, String sortField, boolean ascending, Function<PageRequest, Page<MODEL>> pageableFunction) {
        return getPageable(page, size, sortField, ascending, pageableFunction, mapper::fromModelToResponse);


    }

    public RESPONSE saveBody(BODY body) {
        return mapper.fromModelToResponse(saveFromBody(body));
    }

    protected TransformableWrappers.DefaultMappableWrapper verifyExistsByUniqueField(BODY body, Function<BODY, Pair<Boolean, String>> existsByUniqueField) {
        Pair<Boolean, String> exists = existsByUniqueField.apply(body);
        if (exists.getFirst()) {
            throw new EntityWithUniqueExists(serviceName, exists.getSecond());
        }
        return TransformableWrappers.of();
    }


}
