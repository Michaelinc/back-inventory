package com.incoorp.inventory.web.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommodityDTO implements Serializable {
    
    private String commodityName;

    private Integer amount;

    private Date entryDate;

    private UserDTO user;
}
