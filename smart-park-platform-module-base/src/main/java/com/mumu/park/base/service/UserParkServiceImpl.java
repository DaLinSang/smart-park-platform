package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.park.base.entities.UserParkEntity;
import com.mumu.park.base.repository.UserParkRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户园区关联 Service 实现
 */
@Service
public class UserParkServiceImpl extends ServiceImpl<UserParkRepository, UserParkEntity> implements UserParkService {

    @Override
    public List<UserParkEntity> getByUserId(String userId) {
        LambdaQueryWrapper<UserParkEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserParkEntity::getUserId, userId);
        return list(wrapper);
    }

    @Override
    public boolean deleteByUserId(String userId) {
        LambdaQueryWrapper<UserParkEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserParkEntity::getUserId, userId);
        // MyBatis Plus 逻辑删除：调用 remove 时会自动把 deleted 置为 1
        return remove(wrapper);
    }
}