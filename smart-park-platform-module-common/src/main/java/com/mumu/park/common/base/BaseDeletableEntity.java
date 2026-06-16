package com.mumu.park.common.base;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
public class BaseDeletableEntity extends BaseEntity {

    //逻辑删除标记（0-未删，1-已删）
    @TableLogic(value = "0", delval = "1")
    private Integer deleted;
}
