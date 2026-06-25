package com.mumu.park.base.api;

import com.mumu.park.base.vo.BaseParkPersistableVO;
import com.mumu.park.base.vo.BaseParkVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("base/basePark")
public interface BaseParkApi {

    /** 分页查询园区列表 */
    @GetMapping("/list")
    R<List<BaseParkVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                   @RequestParam(defaultValue = "10") int size);

    /** 查询所有园区 */
    @GetMapping("/getAllParks")
    R<List<BaseParkVO>> getAllParks();

    /** 根据ID查询园区 */
    @GetMapping("/{id}")
    R<BaseParkVO> getById(@PathVariable Long id);

    /** 根据用户ID查询所属园区列表 */
    @GetMapping("/getUserParks/{userId}")
    R<List<BaseParkVO>> getUserParks(@PathVariable String userId);

    /** 新增园区 */
    @PostMapping
    R<BaseParkVO> create(@RequestBody BaseParkPersistableVO vo);

    /** 更新园区 */
    @PutMapping("/{id}")
    R<BaseParkVO> update(@PathVariable Long id, @RequestBody BaseParkPersistableVO vo);

    /** 删除园区 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);
}