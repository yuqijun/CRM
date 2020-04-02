package com.hwua.mapper;

import com.hwua.pojo.Log;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@MapperScan
public interface LogMapper {
    //    查看所有日志
    public List<Log> queryAllLog() throws Exception;
    //保存日志信息
    public Integer saveLog(Log log)throws  Exception;
}