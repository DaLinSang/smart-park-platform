package com.mumu.park.base.api;

import com.mumu.park.base.service.BaseParkBuildingRelationService;
import com.mumu.park.base.vo.BaseParkBuildingRelationPersistableVO;
import com.mumu.park.base.vo.BaseParkBuildingRelationVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseParkBuildingRelationApiImpl implements BaseParkBuildingRelationApi {

    private final BaseParkBuildingRelationService baseParkBuildingRelationService;

    @Override
    public R<List<BaseParkBuildingRelationVO>> listByPage(int page, int size, String parkId) {
        return R.success(baseParkBuildingRelationService.listByPage(page, size, parkId));
    }

    @Override
    public R<BaseParkBuildingRelationVO> getById(Long id) {
        BaseParkBuildingRelationVO vo = baseParkBuildingRelationService.getById(id);
        if (vo == null) {
            return R.error("园区楼栋关系不存在");
        }
        return R.success(vo);
    }

    @Override
    public R<BaseParkBuildingRelationVO> create(BaseParkBuildingRelationPersistableVO vo) {
        return R.success(baseParkBuildingRelationService.create(vo));
    }

    @Override
    public R<BaseParkBuildingRelationVO> update(Long id, BaseParkBuildingRelationPersistableVO vo) {
        BaseParkBuildingRelationVO result = baseParkBuildingRelationService.update(id, vo);
        if (result == null) {
            return R.error("园区楼栋关系不存在");
        }
        return R.success(result);
    }

    @Override
    public R<Void> deleteById(Long id) {
        baseParkBuildingRelationService.deleteById(id);
        return R.success();
    }
}