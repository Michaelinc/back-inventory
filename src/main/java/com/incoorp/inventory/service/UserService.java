/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.UserEntity;
import com.incoorp.inventory.data.repository.UserRepository;
import com.incoorp.inventory.exceptions.responses.BadRequestException;
import com.incoorp.inventory.web.dto.UserDTO;
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
public class UserService {

    @Autowired
    private final UserRepository repository;

    @Autowired
    private final ModelMapper mapper;

    public UserDTO save(UserDTO user) {
        validateInformation(user);
        UserEntity entity = mapper.map(user, UserEntity.class);
        entity = this.repository.save(entity);
        return mapper.map(entity, UserDTO.class);
    }
    public void validateInformation(UserDTO user) {
        if (user.getUserName() == null) {
            throw new BadRequestException("El nombre de usuario es requerido");
        }
    }

    public List<UserDTO> findAll() {
        List<UserDTO> response = new ArrayList<>();
        List<UserEntity> entities = repository.findAll();
        for(UserEntity user : entities){
            response.add(mapper.map(user, UserDTO.class));
        }
        return response;
    }
}
