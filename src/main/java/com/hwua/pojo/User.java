package com.hwua.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;


@AllArgsConstructor
@Data
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable {
    private String id;
    private String email;
    private String username;
    private String password;
    private String phoneNum;
    private Integer status;
    public List<Role> roles;
    public User(){}
    public User(String username , String password){
        this.username = username;
        this.password = password;
    }

}
