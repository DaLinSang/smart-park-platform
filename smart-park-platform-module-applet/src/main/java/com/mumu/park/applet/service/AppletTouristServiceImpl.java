package com.mumu.park.applet.service;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.park.applet.entities.AppletTouristEntity;
import com.mumu.park.applet.repository.AppletTouristRepository;
import org.springframework.stereotype.Service;

/**
 * 游客服务实现
 */
@Service
public class AppletTouristServiceImpl extends ServiceImpl<AppletTouristRepository, AppletTouristEntity> implements AppletTouristService{
}
