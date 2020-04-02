package com.hwua.controller;

import com.hwua.annotation.SystemControllerLog;
import com.hwua.mapper.LogMapper;
import com.hwua.pojo.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
@RequestMapping("/syslog_list")

public class LogController {
    @Autowired
    private LogMapper logMapper ;
    @GetMapping("/showAllSysLog")
    @SystemControllerLog(description = "showAllLog")
    public ModelAndView showAllLog() throws Exception {
        ModelAndView md = new ModelAndView();
        List<Log> sysLogs= logMapper.queryAllLog();
        md.addObject("sysLogs",sysLogs);
        md.setViewName("/pages/syslog-list");
        return md;
    }
}
