package com.mumu.park.applet.api;


import com.mumu.park.applet.vo.AppletTouristPersistableVO;
import com.mumu.park.applet.vo.AppletTouristVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 游客管理的 API 接口
 */
@RequestMapping("/applet/tourist")
public interface AppletTouristApi {

    //小程序页面需要展示全部游客列表，一次性返回所有游客信息，前端用于列表页渲染
    @GetMapping("/list")
    R<List<AppletTouristVO>> list();

    //根据游客唯一 ID，查询单条游客详情
    @GetMapping("/{id}")
    R<AppletTouristVO> getById(@PathVariable Long id);

    //小程序端新增一条游客记录
    @PostMapping
    R<Long> add(@RequestBody AppletTouristPersistableVO vo);

    //根据游客 ID，更新已有游客信息（编辑操作）
    @PutMapping("/{id}")
    R<Boolean> update(@PathVariable Long id, @RequestBody AppletTouristPersistableVO vo);

    //根据游客 ID删除单条游客数据，返回布尔值标记是否删除成功
    @DeleteMapping("/{id}")
    R<Boolean> delete(@PathVariable Long id);



}
