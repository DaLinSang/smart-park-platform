package com.mumu.park.base.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 在园人数 输出VO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkPeopleNumberVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /** 园区ID */
    private String parkId;

    /** 在园总人数 */
    private Integer totalPeople;

    /** 进门人数（当日累计） */
    private Integer enteredCount;

    /** 出门人数（当日累计） */
    private Integer exitedCount;

    /** 在场车辆数 */
    private Integer parkedVehicleCount;
}