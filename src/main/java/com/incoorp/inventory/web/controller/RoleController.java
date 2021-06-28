/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.web.controller;

import com.incoorp.inventory.service.RoleService;
import com.incoorp.inventory.web.dto.RoleDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Michael Rendón Villa
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/role", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class RoleController {
    
    @Autowired
    private final RoleService service;
    
    @PostMapping()
    public RoleDTO save(@RequestBody RoleDTO role){
        return service.save(role);
    }
    
    @GetMapping()
    public List<RoleDTO> findAll() {
        return service.findAll();
    }
    
}
