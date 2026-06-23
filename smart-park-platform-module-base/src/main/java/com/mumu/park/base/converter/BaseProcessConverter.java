package com.mumu.park.base.converter;

import com.mumu.park.base.entities.BaseProcessEntity;
import com.mumu.park.base.vo.BaseProcessPersistableVO;
import com.mumu.park.base.vo.BaseProcessVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BaseProcessConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    BaseProcessPersistableVO toPersistableVO(BaseProcessEntity entity);

    /**
     * VO → Entity（新增）
     */
    BaseProcessEntity toEntity(BaseProcessVO vo);

    /**
     * VO + id → Entity（更新）
     */
    @Mapping(target = "id", source = "id")
    BaseProcessEntity toEntity(Long id, BaseProcessVO vo);
}