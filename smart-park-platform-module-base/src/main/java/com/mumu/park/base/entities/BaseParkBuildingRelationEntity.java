package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 园区楼栋关系实体 - base_park_building_relation
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("base_park_building_relation")
public class BaseParkBuildingRelationEntity extends BaseDeletableEntity {

    /** 园区ID，关联 base_park.id */
    private String parkId;

    /** 楼栋ID，关联 base_building.id */
    private Long buildingId;

    /** 租户ID */
    private Long tenantId;
}