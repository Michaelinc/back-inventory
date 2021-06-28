/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.web.controller;

import com.incoorp.inventory.service.UserService;
import com.incoorp.inventory.web.dto.UserDTO;
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
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class UserController {
    
    @Autowired
    private final UserService service;
    
    @PostMapping()
    public UserDTO save(@RequestBody UserDTO user){
        return service.save(user);
    }
    
    @GetMapping()
    public List<UserDTO> findAll() {
        return service.findAll();
    }
    
}
