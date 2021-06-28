/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.incoorp.inventory.web.controller;

import com.incoorp.inventory.service.CommodityService;
import com.incoorp.inventory.web.dto.CommodityDTO;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Michael Rendón Villa
 */
@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/commodity", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommodityController {
    
    @Autowired
    private final CommodityService service;
    
    @PostMapping()
    public CommodityDTO save(@RequestBody CommodityDTO commodity){
        return service.save(commodity);
    }
    
    @PutMapping()
    public CommodityDTO edit(@RequestBody CommodityDTO commodity, @RequestParam(name = "userId",required = true) Integer userId) {
        return service.edit(commodity,userId);
    }
    
    @DeleteMapping()
    public Boolean delete(@RequestParam(name = "commodityName", required = true) String commodityName,
            @RequestParam(name = "userId", required = true) Integer userId) {
        return service.delete(commodityName,userId);
    }
    
    @GetMapping()
    public List<CommodityDTO> findAll() {
        return service.findAll();
    }
    
    @GetMapping(path = "/findAllByExample")
    public List<CommodityDTO> findAllByExample(@RequestParam(name = "commodityName",required = false) String commodityName,
            @RequestParam(name = "userId", required = false) Integer userId,
            @RequestParam(name = "entryDate", required = false)  Date entryDate) {
        return service.findAllByExample(commodityName,userId,entryDate);
    }
    
}
