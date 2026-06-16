package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.UserParkEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户园区关联Repository
 */
@Mapper
public interface UserParkRepository extends BaseMapper<UserParkEntity> {
}
