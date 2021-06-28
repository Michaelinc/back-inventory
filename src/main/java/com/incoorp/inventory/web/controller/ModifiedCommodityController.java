/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.web.controller;

import com.incoorp.inventory.service.ModifiedCommodityService;
import com.incoorp.inventory.web.dto.ModifiedCommodityDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Michael Rendón Villa
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/modifiedCommodity", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ModifiedCommodityController {

    @Autowired
    private final ModifiedCommodityService service;

    @GetMapping(path = "/findAllByExample")
    public List<ModifiedCommodityDTO> findAllByExample(@RequestParam(name = "commodityName", required = false) String commodityName) {
        return service.findAllByExample(commodityName);
    }

}
