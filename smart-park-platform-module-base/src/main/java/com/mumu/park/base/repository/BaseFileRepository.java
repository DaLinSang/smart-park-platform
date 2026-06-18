package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.BaseFileEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseFileRepository extends BaseMapper<BaseFileEntity> {
}