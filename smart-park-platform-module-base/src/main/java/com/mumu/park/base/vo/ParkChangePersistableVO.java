package com.mumu.park.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 园区变更申请 输出VO（继承审计VO，追加主键ID）
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ParkChangePersistableVO extends ParkChangeAuditableVO {

    /** 主键ID */
    private String id;
}