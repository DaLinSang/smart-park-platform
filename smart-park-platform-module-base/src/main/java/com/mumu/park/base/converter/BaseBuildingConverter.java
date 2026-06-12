package com.mumu.park.base.converter;

import com.mumu.park.base.entities.BaseBuildingEntity;
import com.mumu.park.base.vo.BaseBuildingAuditableVO;
import com.mumu.park.base.vo.BaseBuildingPersistableVO;
import com.mumu.park.base.vo.BaseBuildingVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseBuildingConverter {

    BaseBuildingVO toVO(BaseBuildingEntity entity);

    BaseBuildingAuditableVO toAuditableVO(BaseBuildingEntity entity);

    BaseBuildingEntity toEntity(BaseBuildingPersistableVO vo);
}