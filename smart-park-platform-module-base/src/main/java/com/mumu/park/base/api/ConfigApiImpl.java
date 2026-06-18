package com.mumu.park.base.api;

import com.mumu.park.base.service.ConfigService;
import com.mumu.park.base.vo.ConfigPersistableVO;
import com.mumu.park.base.vo.ConfigVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConfigApiImpl implements ConfigApi {

    private final ConfigService configService;

    @Override
    public R<List<ConfigPersistableVO>> listByPage(int page, int size, String moduleName, String configType) {
        return R.success(configService.listByPage(page, size, moduleName, configType));
    }

    @Override
    public R<ConfigPersistableVO> getById(Long id) {
        ConfigPersistableVO vo = configService.getById(id);
        if (vo == null) {
            return R.error("配置不存在");
        }
        return R.success(vo);
    }

    @Override
    public R<String> getConfigValue(String moduleName, String configType, String configKey) {
        String value = configService.getConfigValue(moduleName, configType, configKey);
        if (value == null) {
            return R.error("未找到配置");
        }
        return R.success(value);
    }

    @Override
    public R<ConfigPersistableVO> create(ConfigVO vo) {
        try {
            return R.success(configService.create(vo));
        } catch (RuntimeException e) {
            return R.error(e.getMessage());
        }
    }

    @Override
    public R<ConfigPersistableVO> update(Long id, ConfigVO vo) {
        ConfigPersistableVO result = configService.update(id, vo);
        if (result == null) {
            return R.error("配置不存在");
        }
        return R.success(result);
    }

    @Override
    public R<Void> deleteById(Long id) {
        configService.deleteById(id);
        return R.success();
    }
}