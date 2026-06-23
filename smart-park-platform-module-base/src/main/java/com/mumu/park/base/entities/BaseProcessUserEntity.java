package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * 处理人员表实体 - t_base_process_user
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_base_process_user")
public class BaseProcessUserEntity extends BaseDeletableEntity {

    /** 处理进度ID */
    private Long processId;

    /** 处理人ID */
    private String processUserId;

    /** 处理人姓名 */
    private String processUserName;

    /** 处理意见 */
    private String comment;

    /** 处理时间 */
    private LocalDateTime createdTime;

    /** 扩展字段 */
    private String extend;
}