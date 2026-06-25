package com.mumu.park.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BaseParkBuildingRelationPersistableVO extends BaseParkBuildingRelationAuditableVO{

    /**
     * 主键ID
     */
    private Long id;
}