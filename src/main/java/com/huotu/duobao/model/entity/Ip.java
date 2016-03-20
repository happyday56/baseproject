package com.huotu.duobao.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * ip地址库
 * Created by lgh on 2016/2/20.
 */
@Getter
@Setter
@Entity
public class Ip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 起始地址
     */
    @Column(length = 50)
    private String startIp;

    /**
     * 结束地址
     */
    @Column(length = 50)
    private String endIp;

    /**
     * 城市
     */
    @Column(length = 50)
    private String cityName;

    /**
     * 运营商
     */
    @Column(length = 100)
    private String operators;

    /**
     * 起始量
     * 根据起始地址计算，用于查找指定ip信息
     */
    private Long startAmount;

    /**
     * 结束量
     */
    private Long endAmount;
}
