package com.hwua.services;

import com.hwua.pojo.Log;

import java.util.List;

public interface LogServices {
    public List<Log> queryAllLog() throws Exception;
    //保存日志信息
    public Integer saveLog(Log log)throws  Exception;
}
