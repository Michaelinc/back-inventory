/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.RoleEntity;
import com.incoorp.inventory.data.repository.RoleRepository;
import com.incoorp.inventory.exceptions.responses.BadRequestException;
import com.incoorp.inventory.exceptions.responses.InternalServerErrorException;
import com.incoorp.inventory.web.dto.RoleDTO;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Michael Rendón Villa
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RoleService {

    @Autowired
    private final RoleRepository repository;

    @Autowired
    private final ModelMapper mapper;

    public RoleDTO save(RoleDTO role) {
        validateInformation(role);
        RoleEntity entity = mapper.map(role, RoleEntity.class);
        entity = this.repository.save(entity);
        return mapper.map(entity, RoleDTO.class);
    }

    public void validateInformation(RoleDTO role) {
        if (role.getRoleName() == null) {
            throw new BadRequestException("El nombre de cargo es requerido");
        }
    }

    public List<RoleDTO> findAll() {
        List<RoleDTO> response = new ArrayList<>();
        Iterable iterable = repository.findAll();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            response.add(mapper.map(iterator.next(), RoleDTO.class));
        }
        return response;
    }
}
