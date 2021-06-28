/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.CommodityEntity;
import com.incoorp.inventory.data.entity.RoleEntity;
import com.incoorp.inventory.data.entity.UserEntity;
import com.incoorp.inventory.data.repository.CommodityRepository;
import com.incoorp.inventory.data.repository.ModifiedCommodityRepository;
import com.incoorp.inventory.data.repository.UserRepository;
import com.incoorp.inventory.exceptions.responses.BadRequestException;
import com.incoorp.inventory.web.dto.CommodityDTO;
import com.incoorp.inventory.web.dto.RoleDTO;
import com.incoorp.inventory.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Michael Rendón Villa
 */
public class CommodityServiceTest {

    @Mock()
    private CommodityRepository commodityRepository;

    @Mock()
    private ModifiedCommodityRepository modifiedCommodityRepository;

    @Mock()
    private UserRepository userRepository;

    private ModelMapper mapper;

    private CommodityService service;

    @BeforeEach
    public void setUp() {
        commodityRepository = Mockito.mock(CommodityRepository.class);
        modifiedCommodityRepository = Mockito.mock(ModifiedCommodityRepository.class);
        userRepository = Mockito.mock(UserRepository.class);
        mapper = new ModelMapper();
        this.service = new CommodityService(commodityRepository,
                modifiedCommodityRepository, userRepository, mapper);

    }

    @Test
    public void testSaveOk() {

        Optional<CommodityEntity> returnedById = Optional.of(new CommodityEntity("name", 20, new Date(), new UserEntity(1, "user", "20", new Date(1), new RoleEntity(1, "role"))));
        when(this.commodityRepository.findById(ArgumentMatchers.anyString())).thenReturn(returnedById);

        CommodityEntity returned = new CommodityEntity("name", 20, new Date(), new UserEntity(1, "user", "20", new Date(1), new RoleEntity(1, "role")));
        when(this.commodityRepository.save(any(CommodityEntity.class))).thenReturn(returned);

        when(this.userRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);

        CommodityDTO sended = new CommodityDTO("name", 20, new Date(), new UserDTO(1, "user", "20", new Date(1), new RoleDTO(1, "role")));
        CommodityDTO expResult = new CommodityDTO("name", 20, new Date(), new UserDTO(1, "user", "20", new Date(1), new RoleDTO(1, "role")));
        CommodityDTO result = this.service.save(sended);
        Assertions.assertEquals(expResult.getCommodityName(), result.getCommodityName());
    }

  /*  @Test
    public void testEditOk() {

        Optional<CommodityEntity> returnedById = Optional.of(new CommodityEntity("name", 25, new Date(), new UserEntity(1, "user", "20", new Date(1), new RoleEntity(1, "role"))));
        when(this.commodityRepository.findById(ArgumentMatchers.anyString())).thenReturn(returnedById);

        CommodityEntity returned = new CommodityEntity("name", 25, new Date(), new UserEntity(1, "user", "20", new Date(1), new RoleEntity(1, "role")));
        when(this.commodityRepository.save(any(CommodityEntity.class))).thenReturn(returned);

        Optional<UserEntity> userOptional = Optional.of(new UserEntity(1, "user", "20", new Date(1), new RoleEntity(1, "role")));
        when(this.userRepository.findById(ArgumentMatchers.anyInt())).thenReturn(userOptional);

        when(this.userRepository.existsById(ArgumentMatchers.anyInt())).thenReturn(true);

        CommodityDTO sended = new CommodityDTO("name", 25, new Date(), new UserDTO(1, "user", "20", new Date(1), new RoleDTO(1, "role")));
        CommodityDTO expResult = new CommodityDTO("name", 25, new Date(), new UserDTO(1, "user", "20", new Date(1), new RoleDTO(1, "role")));
        CommodityDTO result = this.service.edit(sended,1);
        Assertions.assertEquals(expResult.getCommodityName(), result.getCommodityName());
    }*/

    @Test
    public void testSaveCommodityExistById() {
        when(this.commodityRepository.existsById(ArgumentMatchers.anyString())).thenReturn(true);

        Exception thrown = assertThrows(
                Exception.class,
                () -> this.service.save(new CommodityDTO("name", 20, new Date(), new UserDTO(1, "user", "20", new Date(1), new RoleDTO(1, "role")))));
        assertEquals(thrown.getMessage(), "El nombre de esta mercancia ya existe");

    }
    
    @Test
    public void deleteCommodity() {
        Optional<CommodityEntity> returnedById = Optional.of(new CommodityEntity("name", 25, new Date(), new UserEntity(1, "user", "20", new Date(1), new RoleEntity(1, "role"))));
        when(this.commodityRepository.findById(ArgumentMatchers.anyString())).thenReturn(returnedById);
        Mockito.doNothing().when(this.modifiedCommodityRepository).deleteByCommodityCommodityName(ArgumentMatchers.anyString());
        Mockito.doNothing().when(this.commodityRepository).deleteById(ArgumentMatchers.anyString());


    }

    /* @Test
    public void testvalidateInformationReturnExcepion() {
        RoleDTO roleToSave = new RoleDTO(null, null);
        assertThrows(Exception.class, () -> {
            this.service.validateInformation(roleToSave);
        });
    }

    @Test
    public void testfindAllRolesOK() {
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

    }*/
}
