package com.hwua.services.impl;

import com.hwua.mapper.LogMapper;
import com.hwua.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogServices implements com.hwua.services.LogServices {
    @Autowired
    private LogMapper logMapper;
    @Override
    public List<Log> queryAllLog() throws Exception {
        List<Log> logs = logMapper.queryAllLog();
        return logs;
    }

    @Override
    public Integer saveLog(Log log) throws Exception {
        Integer row =null;
        row = logMapper.saveLog(log);
        return row;
    }
}
