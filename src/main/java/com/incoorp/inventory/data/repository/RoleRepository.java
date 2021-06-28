package com.incoorp.inventory.data.repository;

import com.incoorp.inventory.data.entity.RoleEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Integer>, QueryByExampleExecutor<RoleEntity> {

    @Override
    List<RoleEntity> findAll();
}
