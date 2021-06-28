package com.incoorp.inventory.data.repository;

import com.incoorp.inventory.data.entity.CommodityEntity;
import com.incoorp.inventory.data.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CommodityRepository extends CrudRepository<CommodityEntity, String>, QueryByExampleExecutor<CommodityEntity> {

}
