package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role   {
    private String id;
    private String roleName;
    private String roleDesc;
    public Role(String roleName , String roleDesc){
        this.roleDesc=roleDesc;
        this.roleName=roleName;
    }
}
