package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.BaseParkBuildingRelationConverter;
import com.mumu.park.base.entities.BaseParkBuildingRelationEntity;
import com.mumu.park.base.repository.BaseParkBuildingRelationRepository;
import com.mumu.park.base.vo.BaseParkBuildingRelationPersistableVO;
import com.mumu.park.base.vo.BaseParkBuildingRelationVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseParkBuildingRelationServiceImpl implements BaseParkBuildingRelationService {

    private final BaseParkBuildingRelationRepository baseParkBuildingRelationRepository;
    private final BaseParkBuildingRelationConverter baseParkBuildingRelationConverter;

    @Override
    public List<BaseParkBuildingRelationVO> listByPage(int page, int size, String parkId) {
        LambdaQueryWrapper<BaseParkBuildingRelationEntity> wrapper = new LambdaQueryWrapper<>();
        if (parkId != null) {
            wrapper.eq(BaseParkBuildingRelationEntity::getParkId, parkId);
        }
        return baseParkBuildingRelationRepository
                .selectList(wrapper)
                .stream()
                .map(baseParkBuildingRelationConverter::toVO)
                .toList();
    }

    @Override
    public BaseParkBuildingRelationVO getById(Long id) {
        BaseParkBuildingRelationEntity entity = baseParkBuildingRelationRepository.selectById(id);
        if (entity != null) {
            return baseParkBuildingRelationConverter.toVO(entity);
        }
        return null;
    }

    @Override
    public BaseParkBuildingRelationVO create(BaseParkBuildingRelationPersistableVO vo) {
        BaseParkBuildingRelationEntity entity = baseParkBuildingRelationConverter.toEntity(vo);
        baseParkBuildingRelationRepository.insert(entity);
        return baseParkBuildingRelationConverter.toVO(entity);
    }

    @Override
    public BaseParkBuildingRelationVO update(Long id, BaseParkBuildingRelationPersistableVO vo) {
        BaseParkBuildingRelationEntity entity = baseParkBuildingRelationConverter.toEntity(vo);
        entity.setId(id);
        baseParkBuildingRelationRepository.updateById(entity);
        return baseParkBuildingRelationConverter.toVO(entity);
    }

    @Override
    public void deleteById(Long id) {
        baseParkBuildingRelationRepository.deleteById(id);
    }
}