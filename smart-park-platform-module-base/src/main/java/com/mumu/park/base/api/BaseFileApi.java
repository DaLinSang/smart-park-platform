package com.mumu.park.base.api;

import com.mumu.park.base.vo.BaseFilePersistableVO;
import com.mumu.park.base.vo.BaseFileVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("base/file")
public interface BaseFileApi {

    /** 分页查询 */
    @GetMapping("/list")
    R<List<BaseFilePersistableVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(required = false) String moduleId,
                                              @RequestParam(required = false) String moduleDataId);

    /** 根据ID查询 */
    @GetMapping("/{id}")
    R<BaseFilePersistableVO> getById(@PathVariable Long id);

    /** 根据模块查询（查某个业务的所有附件） */
    @GetMapping("/module")
    R<List<BaseFilePersistableVO>> getByModule(@RequestParam String moduleId,
                                               @RequestParam String moduleDataId);

    /** 新增 */
    @PostMapping
    R<BaseFilePersistableVO> create(@RequestBody BaseFileVO vo);

    /** 批量新增 */
    @PostMapping("/batch")
    R<List<BaseFilePersistableVO>> batchCreate(@RequestBody List<BaseFileVO> voList);

    /** 更新 */
    @PutMapping("/{id}")
    R<BaseFilePersistableVO> update(@PathVariable Long id, @RequestBody BaseFileVO vo);

    /** 删除 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);
}