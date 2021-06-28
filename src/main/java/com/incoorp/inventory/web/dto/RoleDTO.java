package com.incoorp.inventory.web.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RoleDTO implements Serializable {

    private Integer roleId;

    private String roleName;

}
