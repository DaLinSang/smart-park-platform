package com.mumu.park.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mumu.park.base.entities.UserParkEntity;

import java.util.List;

/**
 * 用户园区关联 Service
 */
public interface UserParkService extends IService<UserParkEntity> {

    /**
     * 根据用户ID 查询关联的园区列表
     */
    List<UserParkEntity> getByUserId(String userId);

    /**
     * 根据用户ID 软删除关联关系
     */
    boolean deleteByUserId(String userId);
}