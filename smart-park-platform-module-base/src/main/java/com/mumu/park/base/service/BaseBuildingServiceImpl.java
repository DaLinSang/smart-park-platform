package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.BaseBuildingConverter;
import com.mumu.park.base.entities.BaseBuildingEntity;
import com.mumu.park.base.repository.BaseBuildingRepository;
import com.mumu.park.base.vo.BaseBuildingPersistableVO;
import com.mumu.park.base.vo.BaseBuildingVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BaseBuildingServiceImpl implements BaseBuildingService {

    private final BaseBuildingRepository baseBuildingRepository;
    private final BaseBuildingConverter baseBuildingConverter;

    @Override
    public List<BaseBuildingVO> listByPage(int page, int size, String parkId, String buildingName) {
        // 先不分页，全部展示
        // 根据parkId查询，进行分页展示，若未填写parkId，则查询全部
        LambdaQueryWrapper<BaseBuildingEntity> wrapper = new LambdaQueryWrapper<>();
        if (parkId != null && !parkId.isEmpty()) {
            wrapper.eq(BaseBuildingEntity::getParkId, parkId);
        }
        if (buildingName != null && !buildingName.isEmpty()) {
            wrapper.like(BaseBuildingEntity::getBuildingName, buildingName);
        }
        return baseBuildingRepository
                .selectList(wrapper)
                .stream()
                .map(baseBuildingConverter::toVO).toList();
    }

    @Override
    public BaseBuildingVO getById(Long id) {
        BaseBuildingEntity entity = baseBuildingRepository.selectById(id);
        if (entity != null){
            return baseBuildingConverter.toVO(entity);
        }
        return null;
    }

    @Override
    public BaseBuildingVO create(BaseBuildingPersistableVO vo) {
        BaseBuildingEntity entity = baseBuildingConverter.toEntity(vo);
        baseBuildingRepository.insert(entity);
        return baseBuildingConverter.toVO(entity);
    }

    @Override
    public BaseBuildingVO update(Long id, BaseBuildingPersistableVO vo) {
        BaseBuildingEntity entity = baseBuildingConverter.toEntity(vo);
        entity.setId(id);
        baseBuildingRepository.updateById(entity);
        return baseBuildingConverter.toVO(entity);
    }

    @Override
    public void deleteById(Long id) {
        baseBuildingRepository.deleteById(id);
    }
}
