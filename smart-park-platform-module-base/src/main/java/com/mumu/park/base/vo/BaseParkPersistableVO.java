package com.mumu.park.base.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BaseParkPersistableVO {
    private String parkName;
    private String address;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private String city;

}
