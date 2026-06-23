package com.mumu.park.base.converter;

import com.mumu.park.base.entities.ParkChangeEntity;
import com.mumu.park.base.vo.ParkChangePersistableVO;
import com.mumu.park.base.vo.ParkChangeVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ParkChangeConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    ParkChangePersistableVO toPersistableVO(ParkChangeEntity entity);

    /**
     * VO → Entity（新增）
     */
    ParkChangeEntity toEntity(ParkChangeVO vo);

    /**
     * VO + id → Entity（更新）
     */
    @Mapping(target = "id", source = "id")
    ParkChangeEntity toEntity(Long id, ParkChangeVO vo);
}