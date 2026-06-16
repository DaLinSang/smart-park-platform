package com.mumu.park.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础实体类，所有实体的父类，所有数据库表的实体都继承他
 */

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    //主键，由雪花算法自动生成
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    //创建时间（插入时自动填充）
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    //更新时间（插入和更新时自动更新）
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    //创建人（插入时自动填充）
    @TableField(fill = FieldFill.INSERT)
    private String createBy;

    //更新人（插入和更新时自动填充）
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private String updateBy;


}
