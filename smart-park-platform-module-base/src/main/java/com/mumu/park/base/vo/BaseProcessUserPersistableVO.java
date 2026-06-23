package com.mumu.park.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BaseProcessUserPersistableVO extends BaseProcessUserAuditableVO {

    /** 主键ID */
    private String id;

    /** 处理进度ID */
    private String processId;

    /** 处理时间 */
    private LocalDateTime createdTime;


}