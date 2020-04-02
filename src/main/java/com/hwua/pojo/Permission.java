package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private String id;
    private String permissionName;
    private String url;
    public Permission(String permissionName , String url){
        this.permissionName=permissionName;
        this.url=url;
    }
}
