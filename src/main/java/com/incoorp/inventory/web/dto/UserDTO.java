package com.incoorp.inventory.web.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserDTO implements Serializable {

    private Integer userId;
    private String userName;
    private String age;
    private Date companyEntryDate;
    private RoleDTO role;

}
