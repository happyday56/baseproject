package com.huotu.duobao.service.impl;

import com.huotu.duobao.model.entity.ConfigMobile;
import com.huotu.duobao.model.entity.Ip;
import com.huotu.duobao.repository.ConfigMobileRepository;
import com.huotu.duobao.repository.IpRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;


/**
 * Created by Administrator on 2016/3/20.
 */

@Service
public class TransactionServiceImpl implements TransactionService {
    Log log = LogFactory.getLog(TransactionServiceImpl.class);


    @Autowired
    private ConfigMobileRepository configMobileRepository;

    @Autowired
    IpRepository ipRepository;

    @Override
    public void save() {
        log.info("transaction enter ......................");
        ConfigMobile configMobile = new ConfigMobile();
        configMobile.setAreaCode("2");
        configMobile.setAreaName("");
        configMobileRepository.save(configMobile);

        Ip ip = new Ip();
        ip.setId(2L);
        ip.setStartIp(UUID.randomUUID().toString() + UUID.randomUUID().toString());
        ipRepository.save(ip);

        log.info("transaction finished ......................");
    }
}
