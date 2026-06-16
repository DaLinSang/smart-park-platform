package com.mumu.park.base.service;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.park.base.entities.UserOpenidEntity;
import com.mumu.park.base.repository.UserOpenidRepository;
import org.springframework.stereotype.Service;

@Service
public class UserOpenidServiceImpl extends ServiceImpl<UserOpenidRepository, UserOpenidEntity> implements UserOpenidService{
    @Override
    public UserOpenidEntity getByAccountId(String accountId) {
        LambdaQueryWrapper<UserOpenidEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOpenidEntity::getAccountId, accountId);
        return getOne(wrapper);
    }

    @Override
    public UserOpenidEntity getByOpenid(String openid) {
        LambdaQueryWrapper<UserOpenidEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserOpenidEntity::getOpenid, openid);
        return getOne(wrapper);
    }
}
