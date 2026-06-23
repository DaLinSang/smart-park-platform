package com.mumu.park.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 园区变更申请 审计VO（继承输入VO，追加审计字段）
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class ParkChangeAuditableVO extends ParkChangeVO {

    /** 创建者 */
    private String createdBy;

    /** 更新者 */
    private String updatedBy;

    /** 创建时间 */
    private LocalDateTime createTime;

    /** 更新时间 */
    private LocalDateTime updateTime;
}