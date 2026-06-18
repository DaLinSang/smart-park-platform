package com.mumu.park.base.api;

import com.mumu.park.base.vo.ConfigPersistableVO;
import com.mumu.park.base.vo.ConfigVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("base/config")
public interface ConfigApi {

    /** 分页查询配置列表 */
    @GetMapping("/list")
    R<List<ConfigPersistableVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                            @RequestParam(defaultValue = "10") int size,
                                            @RequestParam(required = false) String moduleName,
                                            @RequestParam(required = false) String configType);

    /** 根据ID查询 */
    @GetMapping("/{id}")
    R<ConfigPersistableVO> getById(@PathVariable Long id);

    /** 根据条件查 value */
    @GetMapping("/value")
    R<String> getConfigValue(@RequestParam String moduleName,
                             @RequestParam String configType,
                             @RequestParam String configKey);

    /** 新增（接收ConfigVO，返回ConfigPersistableVO） */
    @PostMapping
    R<ConfigPersistableVO> create(@RequestBody ConfigVO vo);

    /** 更新 */
    @PutMapping("/{id}")
    R<ConfigPersistableVO> update(@PathVariable Long id, @RequestBody ConfigVO vo);

    /** 删除 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);
}