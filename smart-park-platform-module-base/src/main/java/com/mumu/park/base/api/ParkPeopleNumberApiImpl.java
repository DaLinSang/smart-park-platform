package com.mumu.park.base.api;

import com.mumu.park.base.service.ParkPeopleNumberService;
import com.mumu.park.base.vo.ParkPeopleNumberVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParkPeopleNumberApiImpl implements ParkPeopleNumberApi {

    private final ParkPeopleNumberService parkPeopleNumberService;

    @Override
    public R<ParkPeopleNumberVO> getByParkId(String parkId) {
        return R.success(parkPeopleNumberService.getByParkId(parkId));
    }
}