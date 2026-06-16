package com.mumu.park.base.converter;

import com.mumu.park.base.entities.UserParkEntity;
import com.mumu.park.base.vo.UserParkPersistableVO;
import com.mumu.park.base.vo.UserParkVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 用户OpenID转换器
 */
@Mapper(componentModel = "spring")
public interface UserParkConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    UserParkPersistableVO toPersistableVO(UserParkEntity entity);

    /**
     * VO → Entity（新增）
     */
    UserParkEntity toEntity(UserParkVO vo);

    /**
     * VO + id → Entity（更新）
     */
    @Mapping(target = "id", source = "id")
    UserParkEntity toEntity(Long id, UserParkVO vo);
}