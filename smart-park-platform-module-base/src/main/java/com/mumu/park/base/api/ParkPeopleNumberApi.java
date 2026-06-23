package com.mumu.park.base.api;

import com.mumu.park.base.vo.ParkPeopleNumberVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("base/peopleNumber")
public interface ParkPeopleNumberApi {

    /** 根据园区ID查询在园人数 */
    @GetMapping("/{parkId}")
    R<ParkPeopleNumberVO> getByParkId(@PathVariable String parkId);
}