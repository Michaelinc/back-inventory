package com.incoorp.inventory.data.repository;

import com.incoorp.inventory.data.entity.ModifiedCommodityEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ModifiedCommodityRepository extends CrudRepository<ModifiedCommodityEntity, Integer>, QueryByExampleExecutor<ModifiedCommodityEntity> {

    void deleteByCommodityCommodityName(String commodityName);
}
