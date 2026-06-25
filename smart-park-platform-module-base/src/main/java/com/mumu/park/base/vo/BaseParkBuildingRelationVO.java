package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseParkBuildingRelationVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String parkId;

    private Long buildingId;

    private Long tenantId;
}