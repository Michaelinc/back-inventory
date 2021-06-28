/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.RoleEntity;
import com.incoorp.inventory.data.entity.UserEntity;
import com.incoorp.inventory.data.repository.UserRepository;
import com.incoorp.inventory.web.dto.RoleDTO;
import com.incoorp.inventory.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Michael Rendón Villa
 */
public class UserServiceTest {

    @Mock private UserRepository repository;
    private UserService service;
    private ModelMapper mapper;

    
    
    @BeforeEach
    public void setUp () {
        repository = Mockito.mock(UserRepository.class);
        mapper = new ModelMapper();
        this.service = new UserService(repository, mapper);
        
    }

    @Test
    public void testSaveOk() {
        UserEntity returned = new UserEntity(1,"user","20",new Date(1), new RoleEntity(1,"role"));
        when(this.repository.save(any(UserEntity.class))).thenReturn(returned);
        UserDTO sended = new UserDTO(null, "name", "20", new Date(1), new RoleDTO(1,"role"));
        UserDTO expResult = new UserDTO(1, "name", "20", null, null);
        UserDTO result = this.service.save(sended);
        Assertions.assertEquals(expResult.getUserId(),result.getUserId());

    }
    
    @Test
    public void testvalidateInformationReturnExcepion() {
        UserDTO userToSave = new UserDTO(null,null,"20",new Date(1), new RoleDTO(1,"role"));
        assertThrows(Exception.class, () -> {
            this.service.validateInformation(userToSave);
        });
    }
    
    @Test
    public void testfindAllUserOK (){
        List<UserEntity> entites = new ArrayList<>();
        entites.add(new UserEntity(1, "nombre", "20", new Date(1), new RoleEntity(1, "role")));
        entites.add(new UserEntity(2, "nombre", "20", new Date(1), new RoleEntity(1, "role")));
        List<UserDTO> dtos = new ArrayList<>();
        dtos.add(new UserDTO(1, "nombre", "20", new Date(1), new RoleDTO(1, "role")));
        dtos.add(new UserDTO(2, "nombre", "20", new Date(1), new RoleDTO(1, "role")));
        when(this.repository.findAll()).thenReturn(entites);
        List<UserDTO> result = this.service.findAll();
        assertEquals(result.size(), dtos.size());
        assertEquals(result.get(0).getUserId(), dtos.get(0).getUserId());

    }
    
    @Test
    public void testfindAllUserEmpty() {
        List<UserEntity> entites = new ArrayList<>();
        List<UserDTO> dtos = new ArrayList<>();
        when(this.repository.findAll()).thenReturn(entites);
        List<UserDTO> result = this.service.findAll();
        assertEquals(result.size() + dtos.size(), 0);

    }

}
