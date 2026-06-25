package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseParkVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 园区名称 */
    private String parkName;

    /** 园区地址 */
    private String address;

    /** 纬度 */
    private BigDecimal latitude;

    /** 经度 */
    private BigDecimal longitude;

    /** 城市 */
    private String city;
}
