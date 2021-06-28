package com.incoorp.inventory.web.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ModifiedCommodityDTO implements Serializable {

    private Integer id;

    private UserDTO user;

    private CommodityDTO commodity;

    private Date modificationDate;

}
