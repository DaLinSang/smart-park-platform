package com.mumu.park.applet.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 游客信息 - 基础VO
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class AppletTouristVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 园区ID */
    private String parkId;

    /** 游客ID */
    private String touristId;

    /** 游客类型 */
    private String touristType;

    /** 游客姓名 */
    private String touristName;

    /** 手机号 */
    private String phone;

    /** 身份证号 */
    private String idCard;

    /** 访问时间 */
    private LocalDateTime visitTime;

    /** 离开时间 */
    private LocalDateTime leaveTime;

    /** 访问原因 */
    private String reason;

    /** 状态 */
    private Integer status;
}
