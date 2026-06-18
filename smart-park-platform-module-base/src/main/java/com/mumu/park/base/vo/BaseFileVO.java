package com.mumu.park.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseFileVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 模块类型 */
    private String moduleId;

    /** 模块数据id */
    private String moduleDataId;

    /** 文件系统主键ID */
    private String fileId;

    /** 附件名称 */
    private String name;

    /** 附件地址 */
    private String path;

    /** 文件扩展名 */
    private String extensionName;

    /** 租户id */
    private String tenantId;
}