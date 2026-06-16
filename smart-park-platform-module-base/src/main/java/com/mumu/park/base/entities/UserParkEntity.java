package com.mumu.park.base.entities;

import com.baomidou.mybatisplus.annotation.TableName;
import com.mumu.park.common.base.BaseDeletableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_park")
public class UserParkEntity extends BaseDeletableEntity {

    /**
     * 用户主键ID
     */
    private String userId;

    /**
     * 园区ID
     */
    private String parkId;

    /**
     * 租户ID
     */
    private String tenantId;



}
