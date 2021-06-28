/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.service;

import com.incoorp.inventory.data.entity.CommodityEntity;
import com.incoorp.inventory.data.entity.ModifiedCommodityEntity;
import com.incoorp.inventory.data.entity.UserEntity;
import com.incoorp.inventory.data.repository.CommodityRepository;
import com.incoorp.inventory.data.repository.ModifiedCommodityRepository;
import com.incoorp.inventory.data.repository.UserRepository;
import com.incoorp.inventory.exceptions.responses.BadRequestException;
import com.incoorp.inventory.exceptions.responses.InternalServerErrorException;
import com.incoorp.inventory.exceptions.responses.NotFoundException;
import com.incoorp.inventory.web.dto.CommodityDTO;
import com.incoorp.inventory.web.dto.UserDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
public class CommodityService {

    @Autowired
    private final CommodityRepository commodityRepository;

    @Autowired
    private final ModifiedCommodityRepository modifiedCommodityRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final ModelMapper mapper;

    public CommodityDTO save(CommodityDTO commodity) {
        validateInformation(commodity);

        if (commodityRepository.existsById(commodity.getCommodityName())) {
            throw new BadRequestException("El nombre de esta mercancia ya existe");
        } else if (commodity.getEntryDate().after(new Date())) {
            throw new BadRequestException("La fecha de ingreso debe ser igual menor a la fecha actual");
        }else if (!userRepository.existsById(commodity.getUser().getUserId())) {
            throw new NotFoundException("Usuario no encontrado");
        }
        CommodityEntity entity = mapper.map(commodity, CommodityEntity.class);
        entity = this.commodityRepository.save(entity);
        return mapper.map(entity, CommodityDTO.class);
    }

    public CommodityDTO edit(CommodityDTO commodity, Integer modifierUserId) {

        if (commodity.getCommodityName() == null) {
            throw new BadRequestException("EL nombre es requerido");
        }
        Optional<CommodityEntity> optional = commodityRepository.findById(commodity.getCommodityName());
        if (!optional.isPresent()) {
            throw new BadRequestException("La mercancia no existe");
        } else if (commodity.getAmount() == null) {
            throw new BadRequestException("La cantidad es requerida");
        } else if (modifierUserId == null) {
            throw new BadRequestException("El identificador del usuario modificador es requerido");
        }
        Optional<UserEntity> userOptional = userRepository.findById(modifierUserId);

        if (!userOptional.isPresent()) {
            throw new BadRequestException("El usuario modificador no existe");
        }

        CommodityEntity entity = optional.get();

        entity.setAmount(commodity.getAmount());
        entity.setEntryDate(commodity.getEntryDate());
        entity = this.commodityRepository.save(entity);
        if (entity == null) {
            throw new InternalServerErrorException("Ocurrió un problema en el servidor intente luego.");
        }

        ModifiedCommodityEntity modified = new ModifiedCommodityEntity(
                null,
                userOptional.get(),
                entity, new Date());

        modified = modifiedCommodityRepository.save(modified);

        if (modified == null) {
            throw new InternalServerErrorException("Ocurrió un problema en el servidor intente luego");
        }

        return mapper.map(entity, CommodityDTO.class);
    }

    @Transactional
    public boolean delete(String commodityId, Integer userId) {
        Optional<CommodityEntity> optional = commodityRepository.findById(commodityId);
        if (optional.isPresent()) {
            CommodityEntity entity = optional.get();
            if (Objects.equals(entity.getUser().getUserId(), userId)) {
                modifiedCommodityRepository.deleteByCommodityCommodityName(commodityId);
                commodityRepository.deleteById(commodityId);
                return true;
            } else {
                throw new BadRequestException("NO TIENE LOS PERMISOS PARA ELIMINAR ESTA MERCANCIA");
            }
        } else {
            throw new NotFoundException("La mercancia no existe");
        }
    }

    public void validateInformation(CommodityDTO commodity) {
        if (commodity.getCommodityName() == null || commodity.getCommodityName().isEmpty()) {
            throw new BadRequestException("El nombre de la mercancia es requerido");
        } else if (commodity.getUser() == null) {
            throw new BadRequestException("El usuario es requerido");
        } else if (commodity.getUser().getUserId() == null || commodity.getCommodityName().isEmpty()) {
            throw new BadRequestException("El identificador del usuario es requerido");
        } else if (commodity.getAmount() == null) {
            throw new BadRequestException("La cantidad es requerida");
        }
    }

    public List<CommodityDTO> findAll() {
        List<CommodityDTO> response = new ArrayList<>();
        Iterable iterable = commodityRepository.findAll();
        Iterator iterator = iterable.iterator();

        while (iterator.hasNext()) {
            response.add(mapper.map(iterator.next(), CommodityDTO.class));
        }
        return response;
    }

    public List<CommodityDTO> findAllByExample(String commodityName, Integer userId, Date entryDate) {
        CommodityDTO commodity = new CommodityDTO();
        commodity.setEntryDate(entryDate);
        commodity.setUser(new UserDTO(userId, null, null, null, null));
        commodity.setCommodityName(commodityName);
        List<CommodityDTO> response = new ArrayList<>();
        Iterable iterable = commodityRepository.findAll(Example.of(mapper.map(commodity, CommodityEntity.class)));
        Iterator iterator = iterable.iterator();
        while (iterator.hasNext()) {
            response.add(mapper.map(iterator.next(), CommodityDTO.class));
        }
        return response;
    }
}
