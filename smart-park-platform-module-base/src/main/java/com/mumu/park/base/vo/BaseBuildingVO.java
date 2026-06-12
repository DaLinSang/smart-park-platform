package com.mumu.park.base.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseBuildingVO {
    private Long id;
    private Long parkId;
    private String buildingName;
    private Integer floorNumber;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

}
