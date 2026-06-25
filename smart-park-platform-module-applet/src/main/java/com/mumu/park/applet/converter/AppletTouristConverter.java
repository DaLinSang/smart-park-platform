package com.mumu.park.applet.converter;


import com.mumu.park.applet.entities.AppletTouristEntity;
import com.mumu.park.applet.vo.AppletTouristAuditableVO;
import com.mumu.park.applet.vo.AppletTouristPersistableVO;
import com.mumu.park.applet.vo.AppletTouristVO;
import org.mapstruct.Mapper;


/**
 * 用 MapStruct 做 Entity ↔ VO 转换，这是一个 强类型 的转换方案
 * 游客转换器
 */

@Mapper(componentModel = "spring")
public interface AppletTouristConverter {
    //AppletTouristConverter INSTANCE = Mappers.getMapper(AppletTouristConverter.class);

    //将entity转换为VO视图
    AppletTouristVO toVO(AppletTouristEntity entity);

    //将entity转换为带审计字段的VO视图
    AppletTouristAuditableVO toAuditableVO(AppletTouristEntity entity);

    //将VO视图转换为entity
    AppletTouristEntity toEntity(AppletTouristPersistableVO vo);



}
