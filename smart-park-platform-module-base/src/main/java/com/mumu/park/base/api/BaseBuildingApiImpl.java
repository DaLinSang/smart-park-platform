package com.mumu.park.base.api;

import com.mumu.park.base.service.BaseBuildingService;
import com.mumu.park.base.vo.BaseBuildingPersistableVO;
import com.mumu.park.base.vo.BaseBuildingVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseBuildingApiImpl implements BaseBuildingApi {

    private final BaseBuildingService baseBuildingService;

    @Override
    public R<List<BaseBuildingVO>> listByPage(int page, int size, Long parkId) {
        return R.success(baseBuildingService.listByPage(page, size, parkId));
    }

    @Override
    public R<BaseBuildingVO> getById(Long id) {
        BaseBuildingVO vo = baseBuildingService.getById(id);
        if (vo == null) {
            return R.error("楼栋不存在");
        }
        return R.success(vo);
    }

    @Override
    public R<BaseBuildingVO> create(BaseBuildingPersistableVO vo) {
        return R.success(baseBuildingService.create(vo));
    }

    @Override
    public R<BaseBuildingVO> update(Long id, BaseBuildingPersistableVO vo) {
        BaseBuildingVO result = baseBuildingService.update(id, vo);
        if (result == null) {
            return R.error("楼栋不存在");
        }
        return R.success(result);
    }

    @Override
    public R<Void> deleteById(Long id) {
        baseBuildingService.deleteById(id);
        return R.success();
    }
}