package com.mumu.park.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class UserParkPersistableVO extends UserParkAuditableVO{

    /**
     * 主键ID
     */
    private Long id;

    /** 园区名称（联表查询填充） */
    private String parkName;
}
