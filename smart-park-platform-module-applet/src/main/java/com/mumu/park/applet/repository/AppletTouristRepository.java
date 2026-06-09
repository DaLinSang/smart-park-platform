package com.mumu.park.applet.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.applet.entities.AppletTouristEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 游客数据访问层
 * 参考项目用的是 PageableRepository （hectdi 框架），我们用 MyBatis Plus 的 BaseMapper 。
 */
@Mapper
public interface AppletTouristRepository extends BaseMapper<AppletTouristEntity> {
    // BaseMapper 已经提供了：insert/deleteById/updateById/selectById/selectList 等
    // 如果有自定义查询，在这里加方法
}