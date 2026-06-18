package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.ConfigConverter;
import com.mumu.park.base.entities.ConfigEntity;
import com.mumu.park.base.repository.ConfigRepository;
import com.mumu.park.base.vo.ConfigPersistableVO;
import com.mumu.park.base.vo.ConfigVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConfigServiceImpl implements ConfigService {

    private final ConfigRepository configRepository;
    private final ConfigConverter configConverter;

    @Override
    public List<ConfigPersistableVO> listByPage(int page, int size, String moduleName, String configType) {
        LambdaQueryWrapper<ConfigEntity> wrapper = new LambdaQueryWrapper<>();
        if (moduleName != null && !moduleName.isEmpty()) {
            wrapper.eq(ConfigEntity::getModuleName, moduleName);
        }
        if (configType != null && !configType.isEmpty()) {
            wrapper.eq(ConfigEntity::getConfigType, configType);
        }
        return configRepository
                .selectList(wrapper)
                .stream()
                .map(configConverter::toPersistableVO)
                .toList();
    }

    @Override
    public ConfigPersistableVO getById(Long id) {
        ConfigEntity entity = configRepository.selectById(id);
        return entity != null ? configConverter.toPersistableVO(entity) : null;
    }

    @Override
    public String getConfigValue(String moduleName, String configType, String configKey) {
        return configRepository.getConfigValue(moduleName, configType, configKey);
    }

    @Override
    public ConfigPersistableVO create(ConfigVO vo) {
        // 检查是否已存在相同 key
        if (configRepository.countByUniqueKey(vo.getModuleName(), vo.getConfigType(), vo.getConfigKey()) > 0) {
            throw new RuntimeException("配置已存在：" + vo.getModuleName() + "/" + vo.getConfigType() + "/" + vo.getConfigKey());
        }
        ConfigEntity entity = configConverter.toEntity(vo);
        configRepository.insert(entity);
        return configConverter.toPersistableVO(entity);
    }

    @Override
    public ConfigPersistableVO update(Long id, ConfigVO vo) {
        ConfigEntity entity = configRepository.selectById(id);
        if (entity == null) {
            return null;
        }
        entity.setModuleName(vo.getModuleName());
        entity.setConfigType(vo.getConfigType());
        entity.setConfigKey(vo.getConfigKey());
        entity.setConfigValue(vo.getConfigValue());
        entity.setConfigExtend(vo.getConfigExtend());
        entity.setTenantId(vo.getTenantId());
        configRepository.updateById(entity);
        return configConverter.toPersistableVO(entity);
    }

    @Override
    public void deleteById(Long id) {
        configRepository.deleteById(id);
    }
}