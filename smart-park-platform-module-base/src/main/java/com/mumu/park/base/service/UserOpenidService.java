package com.mumu.park.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mumu.park.base.entities.UserOpenidEntity;

/**
 * 用户OpenID绑定 Service
 */
public interface UserOpenidService extends IService<UserOpenidEntity> {

    /**
     * 根据用户账号ID 查询 OpenID 绑定关系
     */
    UserOpenidEntity getByAccountId(String accountId);

    /**
     * 根据 openid 查询绑定关系（微信登录时使用）
     */
    UserOpenidEntity getByOpenid(String openid);
}
