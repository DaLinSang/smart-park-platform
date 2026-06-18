package com.mumu.park.base.converter;

import com.mumu.park.base.entities.BaseFileEntity;
import com.mumu.park.base.vo.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BaseFileConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    BaseFilePersistableVO toPersistableVO(BaseFileEntity entity);

    /**
     * VO → Entity（新增）
     */
    BaseFileEntity toEntity(BaseFileVO vo);

    /**
     * VO + id → Entity（更新）
     */
    @Mapping(target = "id", source = "id")
    BaseFileEntity toEntity(Long id, BaseFileVO vo);
}