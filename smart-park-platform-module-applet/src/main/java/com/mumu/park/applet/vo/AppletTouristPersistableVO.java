package com.mumu.park.applet.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 游客信息 - 新增/修改
 */
@Data
public class AppletTouristPersistableVO {
    private Long parkId;
    private String touristId;
    private String touristType;
    private String touristName;
    private String phone;
    private String idCard;
    private LocalDateTime visitTime;
    private LocalDateTime leaveTime;
    private String reason;
    private Integer status;
}