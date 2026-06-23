package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.BaseProcessEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BaseProcessRepository extends BaseMapper<BaseProcessEntity> {

    /** 根据业务数据ID查询处理进度 */
    @Select("SELECT * FROM t_base_process WHERE module_data_id = #{moduleDataId} AND deleted = 0 ORDER BY create_time DESC")
    List<BaseProcessEntity> getByModuleDataId(String moduleDataId);
}