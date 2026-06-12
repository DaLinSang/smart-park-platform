package com.mumu.park.base.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseParkVO {
    private Long id;
    private String parkName;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String city;
    private String createTime;
    private String updateTime;

}
