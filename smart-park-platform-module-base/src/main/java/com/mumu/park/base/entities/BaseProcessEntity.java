package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

/**
 * 处理进度表实体 - t_base_process
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_base_process")
public class BaseProcessEntity extends BaseDeletableEntity {

    /** 模块数据ID */
    private String moduleDataId;

    /** 操作：0提交 1通过 2驳回 3流转 */
    private Integer action;

    /** 操作名称 */
    private String actionName;

    /** 租户ID */
    private String tenantId;

    /** 处理人列表（非数据库字段） */
    @TableField(exist = false)
    private List<BaseProcessUserEntity> baseProcessUserEntityList;
}