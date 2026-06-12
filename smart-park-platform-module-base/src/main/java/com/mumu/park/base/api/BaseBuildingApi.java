package com.mumu.park.base.api;

import com.mumu.park.base.vo.BaseBuildingPersistableVO;
import com.mumu.park.base.vo.BaseBuildingVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/base/building")
public interface BaseBuildingApi {

    /** 分页查询楼栋（可按园区ID筛选） */
    @GetMapping("/list")
    R<List<BaseBuildingVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(required = false) Long parkId);

    /** 根据ID查询楼栋 */
    @GetMapping("/{id}")
    R<BaseBuildingVO> getById(@PathVariable Long id);

    /** 新增楼栋 */
    @PostMapping
    R<BaseBuildingVO> create(@RequestBody BaseBuildingPersistableVO vo);

    /** 更新楼栋 */
    @PutMapping("/{id}")
    R<BaseBuildingVO> update(@PathVariable Long id, @RequestBody BaseBuildingPersistableVO vo);

    /** 删除楼栋 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);
}