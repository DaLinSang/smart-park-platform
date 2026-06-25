package com.mumu.park.applet.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * 游客信息 - 持久化VO（查询返回时用，带主键）
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class AppletTouristPersistableVO extends AppletTouristAuditableVO {

    /** 主键ID */
    private Long id;
}