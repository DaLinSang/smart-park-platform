package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.ConfigEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ConfigRepository extends BaseMapper<ConfigEntity> {


    /**
     * 根据 moduleName+configType+configKey 查询配置value
     */
    @Select("SELECT config_value FROM t_config " +
            "WHERE module_name = #{moduleName} AND config_type = #{configType} " +
            "AND config_key = #{configKey} AND deleted = 0 LIMIT 1")
    String getConfigValue(String moduleName, String configType, String configKey);

    /**
     * 检查是否存在相同 key 的配置（用于新增时防重复）
     */
    @Select("SELECT COUNT(1) FROM t_config " +
            "WHERE module_name = #{moduleName} AND config_type = #{configType} " +
            "AND config_key = #{configKey} AND deleted = 0")
    int countByUniqueKey(String moduleName, String configType, String configKey);




}
