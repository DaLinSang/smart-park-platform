package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;


/**
 * 用户园区关联 - 基础VO（新增/更新时用）
 */
@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserParkVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户主键ID
     */
    private String userId;

    /**
     * 园区ID
     */
    private String parkId;

    /**
     * 租户ID
     */
    private String tenantId;

}
