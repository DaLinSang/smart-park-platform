package com.mumu.park.base.api;

import com.mumu.park.base.service.BaseParkService;
import com.mumu.park.base.vo.BaseParkPersistableVO;
import com.mumu.park.base.vo.BaseParkVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseParkApiImpl implements BaseParkApi {

    private final BaseParkService baseParkService;

    @Override
    public R<List<BaseParkVO>> listByPage(int page, int size) {
        List<BaseParkVO> list = baseParkService.listByPage(page, size);
        return R.success(list);
    }

    @Override
    public R<List<BaseParkVO>> getAllParks() {
        return R.success(baseParkService.getAllParks());
    }

    @Override
    public R<BaseParkVO> getById(Long id) {
        BaseParkVO vo = baseParkService.getById(id);
        if (vo == null) {
            return R.error("园区不存在");
        }
        return R.success(vo);
    }

    @Override
    public R<BaseParkVO> create(BaseParkPersistableVO vo) {
        return R.success(baseParkService.create(vo));
    }

    @Override
    public R<BaseParkVO> update(Long id, BaseParkPersistableVO vo) {
        BaseParkVO result = baseParkService.update(id, vo);
        if (result == null) {
            return R.error("园区不存在");
        }
        return R.success(result);
    }

    @Override
    public R<Void> deleteById(Long id) {
        try {
            baseParkService.deleteById(id);
            return R.success();
        } catch (RuntimeException e) {
            return R.error(e.getMessage());
        }
    }
}