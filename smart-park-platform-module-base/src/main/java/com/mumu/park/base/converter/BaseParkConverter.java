package com.mumu.park.base.converter;

import com.mumu.park.base.entities.BaseParkEntity;
import com.mumu.park.base.vo.BaseParkAuditableVO;
import com.mumu.park.base.vo.BaseParkPersistableVO;
import com.mumu.park.base.vo.BaseParkVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseParkConverter {

    /** Entity → VO（给前端展示） */
    BaseParkVO toVO(BaseParkEntity entity);

    /** Entity → AuditableVO（审计信息） */
    BaseParkAuditableVO toAuditableVO(BaseParkEntity entity);

    /** PersistableVO（前端参数）→ Entity（存库） */
    BaseParkEntity toEntity(BaseParkPersistableVO vo);
}