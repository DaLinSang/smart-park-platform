package com.mumu.park.base.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


/**
 * 用户OpenID - 持久化VO（查询返回时用，带主键）
 */

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class UserOpenidPersistableVO extends UserOpenidVO{

    /**
     * 主键ID
     */
    private Long id;
}
