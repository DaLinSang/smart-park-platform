package com.mumu.park.base.api;

import com.mumu.park.base.vo.UserParkPersistableVO;
import com.mumu.park.base.vo.UserParkVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/base/userPark")
public interface UserParkApi {

    /**
     * 查询所有用户园区关联
     */
    @GetMapping("/list")
    R<List<UserParkPersistableVO>> list();

    /**
     * 根据主键查询
     */
    @GetMapping("/{id}")
    R<UserParkPersistableVO> getById(@PathVariable Long id);

    /**
     * 根据用户ID 查询关联的园区列表
     */
    @GetMapping("/getByUserId/{userId}")
    R<List<UserParkPersistableVO>> getByUserId(@PathVariable String userId);

    /**
     * 根据用户ID 查询关联的园区列表（带园区名称）
     */
    @GetMapping("/getByUserIdWithParkName/{userId}")
    R<List<UserParkPersistableVO>> getByUserIdWithParkName(@PathVariable String userId);

    /**
     * 新增关联
     */
    @PostMapping
    R<Long> add(@RequestBody UserParkVO vo);

    /**
     * 更新关联
     */
    @PutMapping("/{id}")
    R<Boolean> update(@PathVariable Long id, @RequestBody UserParkVO vo);

    /**
     * 删除关联（软删除）
     */
    @DeleteMapping("/{id}")
    R<Boolean> delete(@PathVariable Long id);

    /**
     * 根据用户ID 删除关联（软删除）
     */
    @DeleteMapping("/deleteByUserId/{userId}")
    R<Boolean> deleteByUserId(@PathVariable String userId);
}