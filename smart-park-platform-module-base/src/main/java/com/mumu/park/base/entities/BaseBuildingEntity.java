package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 楼栋管理实体 - base_building
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("base_building")
public class BaseBuildingEntity extends BaseDeletableEntity {

    /** 所属园区ID，关联 base_park.id */
    private String parkId;

    /** 楼栋名称 */
    private String buildingName;

    /** 楼层层数 */
    private Integer floorNumber;
}