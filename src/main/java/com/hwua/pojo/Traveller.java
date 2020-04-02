package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Traveller {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    private String credentialsType;
    private String credentialsNum;
    private String travellerType;
}
