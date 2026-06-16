package com.mumu.park.base.vo;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class UserOpenidVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 用户账号ID
     */
    private String accountId;

    /**
     * 微信小程序openid
     */
    private String openid;

}
