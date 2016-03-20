package com.huotu.duobao.repository;

import com.huotu.duobao.model.entity.Ip;
import org.luffy.lib.libspring.data.ClassicsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by xhk on 2016/2/20.
 */
public interface IpRepository extends JpaRepository<Ip, Long>, JpaSpecificationExecutor<Ip> {


}
