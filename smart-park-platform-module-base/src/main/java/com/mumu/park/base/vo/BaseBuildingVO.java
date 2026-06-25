package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseBuildingVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 所属园区ID，关联 base_park.id */
    private String parkId;

    /** 楼栋名称 */
    private String buildingName;

    /** 楼层层数 */
    private Integer floorNumber;
}
