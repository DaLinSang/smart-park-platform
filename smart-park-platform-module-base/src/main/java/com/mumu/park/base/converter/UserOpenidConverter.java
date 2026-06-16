package com.mumu.park.base.converter;

import com.mumu.park.base.entities.UserOpenidEntity;
import com.mumu.park.base.vo.UserOpenidPersistableVO;
import com.mumu.park.base.vo.UserOpenidVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


/**
 * 用户OpenID转换器
 */
@Mapper(componentModel = "spring")
public interface UserOpenidConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    UserOpenidPersistableVO toPersistableVO(UserOpenidEntity userOpenidEntity);

    /**
     * VO → Entity（新增）
     */
    UserOpenidEntity toEntity(UserOpenidVO userOpenidVO);

    /**
     * VO + id → Entity（更新）
     */
    @Mapping(target = "id",source = "id")
    UserOpenidEntity toEntity(Long id, UserOpenidVO userOpenidVO);



}
