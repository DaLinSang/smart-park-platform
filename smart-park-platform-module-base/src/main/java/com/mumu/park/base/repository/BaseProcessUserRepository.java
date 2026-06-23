package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.BaseProcessUserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BaseProcessUserRepository extends BaseMapper<BaseProcessUserEntity> {

    /** 根据处理进度ID查询处理人列表 */
    @Select("SELECT * FROM t_base_process_user WHERE process_id = #{processId} AND deleted = 0 ORDER BY created_time ASC")
    List<BaseProcessUserEntity> getByProcessId(Long processId);
}