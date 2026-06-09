package com.mumu.park.applet.api;


import com.mumu.park.applet.converter.AppletTouristConverter;
import com.mumu.park.applet.entities.AppletTouristEntity;
import com.mumu.park.applet.service.AppletTouristService;
import com.mumu.park.applet.vo.AppletTouristPersistableVO;
import com.mumu.park.applet.vo.AppletTouristVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 游客管理 API 实现
 */
@RestController
@RequiredArgsConstructor
public class AppletTouristApiImpl implements AppletTouristApi {

    //将service和converter进行注入

    //业务层，负责所有数据库操作和业务逻辑
    private final AppletTouristService appletTouristService;

    //转换器组件，使用Converter负责对象的类型转换 entity <--> vo
    private final AppletTouristConverter appletTouristConverter;

    @Override
    public R<List<AppletTouristVO>> list() {
        // 1. 调用业务层，从数据库查询出所有游客的【数据库实体集合】
        List<AppletTouristEntity> entityList = appletTouristService.list();
        // 2. 流式遍历：把每个Entity都交给转换器转成前端展示用的VO
        List<AppletTouristVO> voList = entityList.stream()
                .map(appletTouristConverter::toVO)
                .toList();
        return R.success(voList);
    }

    @Override
    public R<AppletTouristVO> getById(Long id) {
        // 1. 根据ID查询数据库实体
        AppletTouristEntity entity = appletTouristService.getById(id);
        // 2. 判空：数据不存在返回错误，存在则转VO返回成功
        if (entity == null){
            return R.error("游客不存在");
        }
        //要先判空再转换！！！
        return R.success(appletTouristConverter.toVO(entity));
    }

    @Override
    public R<Long> add(AppletTouristPersistableVO vo) {
        // 1. 新增游客时，接收到的是表单vo，要首先转换成entity数据库实体
        AppletTouristEntity entity = appletTouristConverter.toEntity(vo);
        // 2. 调用业务层，执行新增入库
        appletTouristService.save(entity);
        // 3. 返回数据库自增生成的主键ID
        return R.success(entity.getId());
    }

    @Override
    public R<Boolean> update(Long id, AppletTouristPersistableVO vo) {
        // 1. 更新游客时，接收到的是表单vo，要首先转换成entity数据库实体
        AppletTouristEntity entity = appletTouristConverter.toEntity(vo);
        // 2. 强制设置ID（兜底操作，确保更新的是指定ID的数据，防止数据丢失）
        entity.setId(id);
        // 3. 执行更新，并返回是否成功
        return R.success(appletTouristService.updateById(entity));
    }

    @Override
    public R<Boolean> delete(Long id) {
        // 直接调用业务层 按照ID删除，返回删除结果true=成功，false=失败/不存在
        return R.success(appletTouristService.removeById(id));
    }
}
