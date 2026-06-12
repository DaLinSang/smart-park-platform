package com.mumu.park.base.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseParkAuditableVO {
    private Long id;
    private String createBy;
    private String updateBy;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
