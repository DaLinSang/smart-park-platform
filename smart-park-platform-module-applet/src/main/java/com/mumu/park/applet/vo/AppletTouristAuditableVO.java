package com.mumu.park.applet.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 游客信息 - 带审计字段
 */
@Data
public class AppletTouristAuditableVO {
    private Long id;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}