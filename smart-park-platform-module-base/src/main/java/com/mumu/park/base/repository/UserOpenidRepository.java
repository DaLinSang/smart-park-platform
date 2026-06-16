package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.UserOpenidEntity;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户OpenID Repository
 */
@Mapper
public interface UserOpenidRepository extends BaseMapper<UserOpenidEntity> {
}
