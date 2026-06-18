package com.mumu.park.base.service;

import com.mumu.park.base.vo.ConfigPersistableVO;
import com.mumu.park.base.vo.ConfigVO;

import java.util.List;

public interface ConfigService {

    /** 分页查询配置列表 */
    List<ConfigPersistableVO> listByPage(int page, int size, String moduleName, String configType);

    /** 根据ID查询 */
    ConfigPersistableVO getById(Long id);

    /** 根据 moduleName+configType+configKey 查 value */
    String getConfigValue(String moduleName, String configType, String configKey);

    /** 新增配置（接收VO，返回PersistableVO） */
    ConfigPersistableVO create(ConfigVO vo);

    /** 更新配置 */
    ConfigPersistableVO update(Long id, ConfigVO vo);

    /** 删除配置 */
    void deleteById(Long id);
}