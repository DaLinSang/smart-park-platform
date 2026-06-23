package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseProcessUserVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 处理人ID */
    private String processUserId;

    /** 处理人姓名 */
    private String processUserName;

    /** 处理意见 */
    private String comment;

    /** 扩展字段 */
    private String extend;
}