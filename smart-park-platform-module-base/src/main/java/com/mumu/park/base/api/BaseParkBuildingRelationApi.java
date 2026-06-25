package com.mumu.park.base.api;

import com.mumu.park.base.vo.BaseParkBuildingRelationPersistableVO;
import com.mumu.park.base.vo.BaseParkBuildingRelationVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("base/baseParkBuildingRelation")
public interface BaseParkBuildingRelationApi {

    /** 分页查询园区楼栋关系（可按园区ID筛选） */
    @GetMapping("/list")
    R<List<BaseParkBuildingRelationVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size,
                                                  @RequestParam(required = false) String parkId);

    /** 根据ID查询 */
    @GetMapping("/{id}")
    R<BaseParkBuildingRelationVO> getById(@PathVariable Long id);

    /** 新增园区楼栋关系 */
    @PostMapping
    R<BaseParkBuildingRelationVO> create(@RequestBody BaseParkBuildingRelationPersistableVO vo);

    /** 更新园区楼栋关系 */
    @PutMapping("/{id}")
    R<BaseParkBuildingRelationVO> update(@PathVariable Long id, @RequestBody BaseParkBuildingRelationPersistableVO vo);

    /** 删除园区楼栋关系 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);
}