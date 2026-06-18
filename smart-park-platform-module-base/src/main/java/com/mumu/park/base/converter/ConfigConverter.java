package com.mumu.park.base.converter;

import com.mumu.park.base.entities.ConfigEntity;
import com.mumu.park.base.vo.ConfigPersistableVO;
import com.mumu.park.base.vo.ConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConfigConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    ConfigPersistableVO toPersistableVO(ConfigEntity entity);

    /**
     * VO → Entity（新增）
     */
    ConfigEntity toEntity(ConfigVO configVO);

    /**
     * VO + id → Entity（更新）
     */
    @Mapping(target = "id",source = "id")
    ConfigEntity toEntity(Long id,ConfigVO vo);


}