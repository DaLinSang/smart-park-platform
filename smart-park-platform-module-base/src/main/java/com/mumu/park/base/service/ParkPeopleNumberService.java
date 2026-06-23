package com.mumu.park.base.service;

import com.mumu.park.base.vo.ParkPeopleNumberVO;

public interface ParkPeopleNumberService {

    /** 根据园区ID查询在园人数 */
    ParkPeopleNumberVO getByParkId(String parkId);
}