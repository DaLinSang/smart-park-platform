package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 园区变更申请表实体 - t_park_change
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_park_change")
public class ParkChangeEntity extends BaseDeletableEntity {

    /** 申请人ID */
    private String applicantUserId;

    /** 原所属园区ID */
    private String originParkId;

    /** 申请园区ID（逗号分隔，可申请多个） */
    private String applyingParkId;

    /** 角色ID */
    private String roleId;

    /** 原部门ID */
    private String originDepartmentId;

    /** 申请部门ID */
    private String applyingDepartmentId;

    /** 申请原因 */
    private String reason;

    /** 审批状态：0待审批 1流转中 2已审批 3已驳回 */
    private Integer approvalStatus;

    /** 已审批通过的园区ID */
    private String approvedParkId;

    /** 租户ID */
    private String tenantId;
}