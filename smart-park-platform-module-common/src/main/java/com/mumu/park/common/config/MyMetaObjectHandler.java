package com.mumu.park.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        //字段值说明：MaBatis元数据对象用于封装实体属性、实体类属性名、函数式接口提供填充值、字段数据类型
        this.strictInsertFill(metaObject,"createTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictInsertFill(metaObject,"createBy",()-> "system", String.class);
        this.strictInsertFill(metaObject,"updateBy",()-> "system", String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime::now, LocalDateTime.class);
        this.strictUpdateFill(metaObject, "updateBy", () -> "system", String.class);
    }
}
