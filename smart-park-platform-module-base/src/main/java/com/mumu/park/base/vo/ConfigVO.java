package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class ConfigVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 配置所属模块
     */
    private String moduleName;

    /**
     * 配置类型
     */
    private String configType;

    /**
     * 配置key
     */
    private String configKey;

    /**
     * 配置value
     */
    private String configValue;

    /**
     * 扩展字段
     */
    private String configExtend;

    /**
     * 租户ID
     */
    private String tenantId;
}
