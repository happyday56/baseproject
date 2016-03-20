package com.huotu.duobao.service.impl;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

@Service
public class AppService implements ApplicationListener<ContextRefreshedEvent> {


    @Autowired
    TransactionService transactionService;

    private static Log log = LogFactory.getLog(AppService.class);

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (event.getApplicationContext().getParent() == null) {
            transactionService.save();
        }
    }


}
