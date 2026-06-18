package com.mumu.park.base.entities;


import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_config")
public class ConfigEntity extends BaseDeletableEntity {

    /** 配置所属模块 */
    private String moduleName;

    /** 配置类型 */
    private String configType;

    /** 配置key */
    private String configKey;

    /** 配置value */
    private String configValue;

    /** 扩展字段 */
    private String configExtend;

    /** 租户ID */
    private String tenantId;

}
