package com.mumu.park.base.api;

import com.mumu.park.base.vo.UserOpenidPersistableVO;
import com.mumu.park.base.vo.UserOpenidVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/base/userOpenid")
public interface UserOpenidApi {

    /**
     * 查询所有 OpenID 绑定记录
     */
    @GetMapping("/list")
    R<List<UserOpenidPersistableVO>> list();

    /**
     * 根据主键查询
     */
    @GetMapping("/{id}")
    R<UserOpenidPersistableVO> getById(@PathVariable Long id);

    /**
     * 根据用户账号ID 查询
     */
    @GetMapping("/getByAccountId/{accountId}")
    R<UserOpenidPersistableVO> getByAccountId(@PathVariable String accountId);

    /**
     * 新增绑定
     */
    @PostMapping
    R<Long> add(@RequestBody UserOpenidVO vo);

    /**
     * 更新绑定
     */
    @PutMapping("/{id}")
    R<Boolean> update(@PathVariable Long id, @RequestBody UserOpenidVO vo);

    /**
     * 删除绑定
     */
    @DeleteMapping("/{id}")
    R<Boolean> delete(@PathVariable Long id);
}