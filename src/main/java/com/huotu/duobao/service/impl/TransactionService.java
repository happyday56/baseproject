package com.huotu.duobao.service.impl;

import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/3/20.
 */
public interface TransactionService {

    @Transactional
    void save();
}
