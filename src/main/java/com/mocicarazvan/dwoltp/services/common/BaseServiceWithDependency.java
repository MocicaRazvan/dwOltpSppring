package com.mocicarazvan.dwoltp.services.common;

import com.mocicarazvan.dwoltp.dtos.common.DependencyId;
import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import org.springframework.data.jpa.repository.JpaRepository;


public abstract class BaseServiceWithDependency<ID, DEPID, MODEL, BODY extends DependencyId<DEPID>, RESPONSE, REPOSITORY extends JpaRepository<MODEL, ID>,
        DEP>
        extends BaseService<ID, MODEL, BODY, RESPONSE, REPOSITORY> {

    private final GetModel<DEP, DEPID> dependencyGetter;

    public BaseServiceWithDependency(REPOSITORY repository, DtosModelMapper<BODY, MODEL, RESPONSE> mapper, String serviceName, GetModel<DEP, DEPID> dependencyGetter) {
        super(repository, mapper, serviceName);
        this.dependencyGetter = dependencyGetter;
    }

    public DEP getDependency(DEPID id) {
        return dependencyGetter.getModelById(id);
    }

    public abstract MODEL setDependency(BODY body, DEP dependency);

    @Override
    public RESPONSE saveBody(BODY body) {
        return mapper.fromModelToResponse(repository.save(setDependency(body, getDependency(body.getDependencyId()))));
    }
}
