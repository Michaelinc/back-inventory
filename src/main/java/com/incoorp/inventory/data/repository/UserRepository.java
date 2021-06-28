package com.incoorp.inventory.data.repository;

import com.incoorp.inventory.data.entity.UserEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer>, QueryByExampleExecutor<UserEntity> {
    @Override
    List<UserEntity> findAll();
}
