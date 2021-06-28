/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.RoleEntity;
import com.incoorp.inventory.data.entity.UserEntity;
import com.incoorp.inventory.data.repository.RoleRepository;
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
public class RoleServiceTest {

    @Mock private RoleRepository repository;
    private RoleService service;
    private ModelMapper mapper;

    
    
    @BeforeEach
    public void setUp () {
        repository = Mockito.mock(RoleRepository.class);
        mapper = new ModelMapper();
        this.service = new RoleService(repository, mapper);
        
    }

    @Test
    public void testSaveOk() {
        RoleEntity returned = new RoleEntity(1,"role");
        when(this.repository.save(any(RoleEntity.class))).thenReturn(returned);
        RoleDTO sended = new RoleDTO(null,"role");
        RoleDTO expResult = new RoleDTO(1,"role");
        RoleDTO result = this.service.save(sended);
        Assertions.assertEquals(expResult.getRoleId(),result.getRoleId());

    }
    
    @Test
    public void testvalidateInformationReturnExcepion() {
        RoleDTO roleToSave = new RoleDTO(null,null);
        assertThrows(Exception.class, () -> {
            this.service.validateInformation(roleToSave);
        });
    }
    
    @Test
    public void testfindAllRolesOK (){
        List<RoleEntity> entites = new ArrayList<>();
        entites.add(new RoleEntity(1, "role"));
        entites.add(new RoleEntity(2, "role 2"));
        List<RoleDTO> dtos = new ArrayList<>();
        dtos.add(new RoleDTO(1, "role"));
        dtos.add(new RoleDTO(1, "role"));
        when(this.repository.findAll()).thenReturn(entites);
        List<RoleDTO> result = this.service.findAll();
        assertEquals(result.size(), dtos.size());
        assertEquals(result.get(0).getRoleId(), dtos.get(0).getRoleId());

    }
    
    @Test
    public void testfindAllRoleEmpty() {
        List<RoleEntity> entites = new ArrayList<>();
        List<RoleDTO> dtos = new ArrayList<>();
        when(this.repository.findAll()).thenReturn(entites);
        List<RoleDTO> result = this.service.findAll();
        assertEquals(result.size() + dtos.size(), 0);

    }

}
