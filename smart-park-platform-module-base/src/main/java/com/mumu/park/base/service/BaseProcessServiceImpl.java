package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.BaseProcessConverter;
import com.mumu.park.base.converter.BaseProcessUserConverter;
import com.mumu.park.base.entities.BaseProcessEntity;
import com.mumu.park.base.entities.BaseProcessUserEntity;
import com.mumu.park.base.repository.BaseProcessRepository;
import com.mumu.park.base.repository.BaseProcessUserRepository;
import com.mumu.park.base.vo.BaseProcessPersistableVO;
import com.mumu.park.base.vo.BaseProcessVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseProcessServiceImpl implements BaseProcessService {

    private final BaseProcessRepository baseProcessRepository;
    private final BaseProcessUserRepository baseProcessUserRepository;
    private final BaseProcessConverter baseProcessConverter;
    private final BaseProcessUserConverter baseProcessUserConverter;

    @Override
    public List<BaseProcessPersistableVO> listByPage(int page, int size, String moduleDataId) {
        LambdaQueryWrapper<BaseProcessEntity> wrapper = new LambdaQueryWrapper<>();
        if (moduleDataId != null && !moduleDataId.isEmpty()) {
            wrapper.eq(BaseProcessEntity::getModuleDataId, moduleDataId);
        }
        return baseProcessRepository.selectList(wrapper)
                .stream()
                .map(this::enrichWithUsers)
                .toList();
    }

    @Override
    public List<BaseProcessPersistableVO> getByModuleDataId(String moduleDataId) {
        List<BaseProcessEntity> entities = baseProcessRepository.getByModuleDataId(moduleDataId);
        return entities.stream()
                .map(this::enrichWithUsers)
                .toList();
    }

    @Override
    public BaseProcessPersistableVO create(BaseProcessVO vo) {
        BaseProcessEntity entity = baseProcessConverter.toEntity(vo);
        baseProcessRepository.insert(entity);

        // 保存处理人
        if (vo.getBaseProcessUserVOList() != null) {
            List<BaseProcessUserEntity> userEntities = vo.getBaseProcessUserVOList().stream()
                    .map(baseProcessUserConverter::toEntity)
                    .peek(u -> u.setProcessId(entity.getId()))
                    .toList();
            userEntities.forEach(baseProcessUserRepository::insert);
        }

        return enrichWithUsers(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<BaseProcessPersistableVO> batchCreate(List<BaseProcessVO> voList) {
        List<BaseProcessPersistableVO> result = new ArrayList<>();
        for (BaseProcessVO vo : voList) {
            result.add(create(vo));
        }
        return result;
    }

    @Override
    public void deleteById(Long id) {
        baseProcessRepository.deleteById(id);
    }

    /** 填充处理人列表 */
    private BaseProcessPersistableVO enrichWithUsers(BaseProcessEntity entity) {
        BaseProcessPersistableVO vo = baseProcessConverter.toPersistableVO(entity);
        List<BaseProcessUserEntity> users = baseProcessUserRepository.getByProcessId(entity.getId());
        vo.setBaseProcessUserVOList(users.stream()
                .map(baseProcessUserConverter::toPersistableVO)
                .map(baseProcessUserConverter::toVO)
                .toList());
        return vo;
    }
}