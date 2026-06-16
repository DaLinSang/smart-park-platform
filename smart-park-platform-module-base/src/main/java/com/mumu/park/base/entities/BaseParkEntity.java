package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * 园区管理实体 - base_park
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
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