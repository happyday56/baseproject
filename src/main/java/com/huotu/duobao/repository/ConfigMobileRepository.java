package com.huotu.duobao.repository;

import com.huotu.duobao.model.entity.ConfigMobile;
import org.luffy.lib.libspring.data.ClassicsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author CJ
 */
@Repository
public interface ConfigMobileRepository extends JpaRepository<ConfigMobile,Long>,ClassicsRepository<ConfigMobile> {


}
