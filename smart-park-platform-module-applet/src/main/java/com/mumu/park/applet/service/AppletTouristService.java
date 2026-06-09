package com.mumu.park.applet.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.mumu.park.applet.entities.AppletTouristEntity;

/**
 * 通过继承 MyBatis-Plus 的 IService 接口，完成service层的操作
 * 游客服务接口
 */
public interface AppletTouristService extends IService<AppletTouristEntity> {
    // IService 已经提供了 save/remove/update/get/list/page 等方法
}
