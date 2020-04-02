package com.hwua.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Log {
    private String id;
    private String visitTime;
    private String userName;
    private String ip;
    private String url;
    private String executionTime;
    private String method;

}
