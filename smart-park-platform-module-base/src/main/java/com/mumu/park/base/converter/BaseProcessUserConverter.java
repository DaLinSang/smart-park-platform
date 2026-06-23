package com.mumu.park.base.converter;

import com.mumu.park.base.entities.BaseProcessUserEntity;
import com.mumu.park.base.vo.BaseProcessUserPersistableVO;
import com.mumu.park.base.vo.BaseProcessUserVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseProcessUserConverter {

    /**
     * Entity → PersistableVO（查询返回）
     */
    BaseProcessUserPersistableVO toPersistableVO(BaseProcessUserEntity entity);

    /**
     * VO → Entity（新增）
     */
    BaseProcessUserEntity toEntity(BaseProcessUserVO vo);

    /**
     * VO + id → Entity（更新）
     */
    BaseProcessUserEntity toEntity(Long id, BaseProcessUserVO vo);


    /**
     * PersistableVO → VO（父类降级，用于嵌套到 BaseProcessVO.baseProcessUserVOList）
     */
    BaseProcessUserVO toVO(BaseProcessUserPersistableVO persistableVO);
}