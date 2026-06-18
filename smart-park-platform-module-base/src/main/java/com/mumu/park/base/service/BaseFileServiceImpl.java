package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.BaseFileConverter;
import com.mumu.park.base.entities.BaseFileEntity;
import com.mumu.park.base.repository.BaseFileRepository;
import com.mumu.park.base.vo.BaseFilePersistableVO;
import com.mumu.park.base.vo.BaseFileVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseFileServiceImpl implements BaseFileService {

    private final BaseFileRepository baseFileRepository;
    private final BaseFileConverter baseFileConverter;

    @Override
    public List<BaseFilePersistableVO> listByPage(int page, int size, String moduleId, String moduleDataId) {
        LambdaQueryWrapper<BaseFileEntity> wrapper = new LambdaQueryWrapper<>();
        if (moduleId != null && !moduleId.isEmpty()) {
            wrapper.eq(BaseFileEntity::getModuleId, moduleId);
        }
        if (moduleDataId != null && !moduleDataId.isEmpty()) {
            wrapper.eq(BaseFileEntity::getModuleDataId, moduleDataId);
        }
        return baseFileRepository.selectList(wrapper)
                .stream()
                .map(baseFileConverter::toPersistableVO)
                .toList();
    }

    @Override
    public BaseFilePersistableVO getById(Long id) {
        BaseFileEntity entity = baseFileRepository.selectById(id);
        return entity != null ? baseFileConverter.toPersistableVO(entity) : null;
    }

    @Override
    public List<BaseFilePersistableVO> getByModule(String moduleId, String moduleDataId) {
        LambdaQueryWrapper<BaseFileEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BaseFileEntity::getModuleId, moduleId);
        wrapper.eq(BaseFileEntity::getModuleDataId, moduleDataId);
        return baseFileRepository.selectList(wrapper)
                .stream()
                .map(baseFileConverter::toPersistableVO)
                .toList();
    }

    @Override
    public BaseFilePersistableVO create(BaseFileVO vo) {
        BaseFileEntity entity = baseFileConverter.toEntity(vo);
        baseFileRepository.insert(entity);
        return baseFileConverter.toPersistableVO(entity);
    }

    @Override
    public List<BaseFilePersistableVO> batchCreate(List<BaseFileVO> voList) {
        List<BaseFileEntity> entities = voList.stream()
                .map(baseFileConverter::toEntity)
                .toList();
        entities.forEach(baseFileRepository::insert);
        return entities.stream()
                .map(baseFileConverter::toPersistableVO)
                .toList();
    }

    @Override
    public BaseFilePersistableVO update(Long id, BaseFileVO vo) {
        BaseFileEntity entity = baseFileRepository.selectById(id);
        if (entity == null) {
            return null;
        }
        entity.setModuleId(vo.getModuleId());
        entity.setModuleDataId(vo.getModuleDataId());
        entity.setFileId(vo.getFileId());
        entity.setName(vo.getName());
        entity.setPath(vo.getPath());
        entity.setExtensionName(vo.getExtensionName());
        entity.setTenantId(vo.getTenantId());
        baseFileRepository.updateById(entity);
        return baseFileConverter.toPersistableVO(entity);
    }

    @Override
    public void deleteById(Long id) {
        baseFileRepository.deleteById(id);
    }
}