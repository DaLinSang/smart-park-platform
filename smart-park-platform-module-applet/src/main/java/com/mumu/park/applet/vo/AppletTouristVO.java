package com.mumu.park.applet.vo;


import lombok.Data;

import java.time.LocalDateTime;


/**
 * 游客信息 - 查询返回
 */
@Data
public class AppletTouristVO {
    private Long id;
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
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
