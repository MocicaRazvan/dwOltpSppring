package com.mocicarazvan.dwoltp.services.common;

import com.mocicarazvan.dwoltp.mappers.common.DtosModelMapper;
import com.mocicarazvan.dwoltp.utils.GetId;
import com.mocicarazvan.dwoltp.utils.WrapErrorFuture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Pair;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public abstract class BaseService2Dependencies
        <ID, DEPID1, DEPID2, MODEL extends GetId<ID>, BODY, RESPONSE, REPOSITORY extends JpaRepository<MODEL, ID>,
                DEP1, DEP2> extends BaseService<ID, MODEL, BODY, RESPONSE, REPOSITORY> {

    private final Executor executor = Executors.newVirtualThreadPerTaskExecutor();
    private final GetModel<DEP1, DEPID1> dependencyGetter1;
    private final GetModel<DEP2, DEPID2> dependencyGetter2;

    public BaseService2Dependencies(REPOSITORY repository, DtosModelMapper<BODY, MODEL, RESPONSE> mapper, String serviceName, GetModel<DEP1, DEPID1> dependencyGetter1, GetModel<DEP2, DEPID2> dependencyGetter2) {
        super(repository, mapper, serviceName);
        this.dependencyGetter1 = dependencyGetter1;
        this.dependencyGetter2 = dependencyGetter2;
    }

    public abstract Pair<DEPID1, DEPID2> getDependencyIds(BODY body);

    public abstract MODEL setDependencies(BODY body, Pair<DEP1, DEP2> dependencies);

    public abstract MODEL setDependencies(BODY body, Pair<DEP1, DEP2> dependencies, ID id);

    public Pair<DEP1, DEP2> getDependencies(Pair<DEPID1, DEPID2> dependencyIds) {
        CompletableFuture<DEP1> dep1Future = CompletableFuture.supplyAsync(() -> dependencyGetter1.getModelById(dependencyIds.getFirst()), executor);
        CompletableFuture<DEP2> dep2Future = CompletableFuture.supplyAsync(() -> dependencyGetter2.getModelById(dependencyIds.getSecond()), executor);

        return WrapErrorFuture.wrapCallable(() -> Pair.of(dep1Future.join(), dep2Future.join()), serviceName);


    }

    @Override
    public RESPONSE saveBody(BODY body) {
        return mapper.fromModelToResponse(repository.save(setDependencies(body, getDependencies(getDependencyIds(body)))));
    }

    public RESPONSE saveBodyUpdate(BODY body, ID id) {
        return mapper.fromModelToResponse(repository.save(setDependencies(body, getDependencies(getDependencyIds(body)), id)));
    }

    @Override
    public RESPONSE update(ID id, BODY body) {
        return existsById(id).mapToValue(_ -> verifyExistsByUniqueField(body, id).mapToValue(_ ->
                saveBodyUpdate(body, id)
        ));
    }
}
