package com.mumu.park.base.api;

import com.mumu.park.base.vo.BaseProcessPersistableVO;
import com.mumu.park.base.vo.BaseProcessVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("base/process")
public interface BaseProcessApi {

    /** 分页查询 */
    @GetMapping("/list")
    R<List<BaseProcessPersistableVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                                 @RequestParam(defaultValue = "10") int size,
                                                 @RequestParam(required = false) String moduleDataId);

    /** 根据业务数据ID查询（核心） */
    @GetMapping("/module/{moduleDataId}")
    R<List<BaseProcessPersistableVO>> getByModuleDataId(@PathVariable String moduleDataId);

    /** 新增 */
    @PostMapping
    R<BaseProcessPersistableVO> create(@RequestBody BaseProcessVO vo);

    /** 批量新增 */
    @PostMapping("/batch")
    R<List<BaseProcessPersistableVO>> batchCreate(@RequestBody List<BaseProcessVO> voList);

    /** 删除 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);
}