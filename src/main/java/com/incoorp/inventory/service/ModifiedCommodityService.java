/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.CommodityEntity;
import com.incoorp.inventory.data.entity.ModifiedCommodityEntity;
import com.incoorp.inventory.data.repository.ModifiedCommodityRepository;
import com.incoorp.inventory.web.dto.ModifiedCommodityDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michael Rendón Villa
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ModifiedCommodityService {

    @Autowired
    private final ModifiedCommodityRepository modifiedCommodityRepository;

    @Autowired
    private final ModelMapper mapper;


    public List<ModifiedCommodityDTO> findAllByExample(String commodityName) {
        CommodityEntity commodity = new CommodityEntity();
        commodity.setCommodityName(commodityName);
        ModifiedCommodityEntity entity = new ModifiedCommodityEntity(null, null, commodity ,null);
        List<ModifiedCommodityDTO> response = new ArrayList<>();
        Iterable iterable = modifiedCommodityRepository.findAll(Example.of(entity));
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            response.add(mapper.map(iterator.next(), ModifiedCommodityDTO.class));
        }
        return response;
    }
}
