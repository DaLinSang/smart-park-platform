package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 园区管理实体 - base_park
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("base_park")
public class BaseParkEntity extends BaseDeletableEntity {

    /** 园区名称 */
    private String parkName;

    /** 园区详细地址 */
    private String address;

    /** 经度 */
    private BigDecimal longitude;

    /** 纬度 */
    private BigDecimal latitude;

    /** 城市 */
    private String city;
}