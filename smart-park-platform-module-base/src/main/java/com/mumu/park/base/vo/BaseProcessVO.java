package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseProcessVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 模块数据ID */
    private String moduleDataId;

    /** 操作：0提交 1通过 2驳回 3流转 */
    private Integer action;

    /** 操作名称 */
    private String actionName;

    /** 租户ID */
    private String tenantId;

    /** 扩展字段 */
    private String extend;

    /** 处理人列表 */
    private List<BaseProcessUserVO> baseProcessUserVOList;
}