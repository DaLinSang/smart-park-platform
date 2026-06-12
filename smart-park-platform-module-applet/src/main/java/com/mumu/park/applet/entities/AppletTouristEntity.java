package com.mumu.park.applet.entities;


import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 小程序首页游客访问数据实体 applet_tourist
 */

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("applet_tourist")
public class AppletTouristEntity extends BaseDeletableEntity {

    //园区ID
    private Long parkId;

    //访客ID(微信openid等)
    private String touristId;

    //访客类型
    private String touristType;

    //访客姓名
    private String touristName;

    //手机号
    private String phone;

    //身份证号
    private String idCard;

    //访问时间
    private LocalDateTime visitTime;

    //离开时间
    private LocalDateTime leaveTime;

    //访问原因
    private String reason;

    //状态（0=待审核，1=已通过，2=已拒绝）
    private Integer status;







}
