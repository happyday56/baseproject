package com.huotu.duobao.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 电话配置
 *
 * @author CJ
 */
@Entity
@Getter
@Setter
public class ConfigMobile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 11)
    private String number;
    @Column(length = 20)
    private String type;
    @Column(length = 50)
    private String areaName;
    @Column(length = 10)
    private String areaCode;
    @Column(length = 6)
    private String postCode;


}
