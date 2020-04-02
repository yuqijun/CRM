package com.hwua.init;

import com.hwua.services.impl.LuceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProRunner implements ApplicationRunner {
    @Autowired
    private LuceneService luceneService;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("init......");
        //最好先做删除索引库中的内容
        luceneService.createIndex();//创建索引库
    }
}
