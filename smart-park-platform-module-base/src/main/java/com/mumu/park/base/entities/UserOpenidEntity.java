package com.mumu.park.base.entities;


import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_openid")
public class UserOpenidEntity extends BaseEntity {

    //因为解绑时物理删除，不需要软删除，所以直接继承BaseEntity就可以，不用BaseDeletableEntity
    /**
     * 用户账号
     */
    private String accountId;

    /**
     * 微信小程序openid
     */
    private String openid;



}
