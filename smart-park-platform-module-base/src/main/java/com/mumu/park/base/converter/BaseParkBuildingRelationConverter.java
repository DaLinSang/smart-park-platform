package com.mumu.park.base.converter;

import com.mumu.park.base.entities.BaseParkBuildingRelationEntity;
import com.mumu.park.base.vo.BaseParkBuildingRelationAuditableVO;
import com.mumu.park.base.vo.BaseParkBuildingRelationPersistableVO;
import com.mumu.park.base.vo.BaseParkBuildingRelationVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseParkBuildingRelationConverter {

    BaseParkBuildingRelationVO toVO(BaseParkBuildingRelationEntity entity);

    BaseParkBuildingRelationEntity toEntity(BaseParkBuildingRelationPersistableVO vo);

    BaseParkBuildingRelationEntity toEntity(Long id, BaseParkBuildingRelationPersistableVO vo);
}