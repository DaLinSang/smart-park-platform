package com.mumu.park.base.api;

import com.mumu.park.base.service.ParkChangeService;
import com.mumu.park.base.vo.ParkChangePersistableVO;
import com.mumu.park.base.vo.ParkChangeVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ParkChangeApiImpl implements ParkChangeApi {

    private final ParkChangeService parkChangeService;

    @Override
    public R<List<ParkChangePersistableVO>> listByPage(int page, int size, String applicantUserId, Integer approvalStatus) {
        return R.success(parkChangeService.listByPage(page, size, applicantUserId, approvalStatus));
    }

    @Override
    public R<ParkChangePersistableVO> getById(Long id) {
        ParkChangePersistableVO vo = parkChangeService.getById(id);
        if (vo == null) {
            return R.error("申请记录不存在");
        }
        return R.success(vo);
    }

    @Override
    public R<ParkChangePersistableVO> create(ParkChangeVO vo) {
        return R.success(parkChangeService.create(vo));
    }

    @Override
    public R<ParkChangePersistableVO> update(Long id, ParkChangeVO vo) {
        try {
            return R.success(parkChangeService.update(id, vo));
        } catch (RuntimeException e) {
            return R.error(e.getMessage());
        }
    }

    @Override
    public R<Void> deleteById(Long id) {
        parkChangeService.deleteById(id);
        return R.success();
    }

    @Override
    public R<ParkChangePersistableVO> approve(Long id) {
        try {
            return R.success(parkChangeService.approve(id));
        } catch (RuntimeException e) {
            return R.error(e.getMessage());
        }
    }

    @Override
    public R<ParkChangePersistableVO> reject(Long id) {
        try {
            return R.success(parkChangeService.reject(id));
        } catch (RuntimeException e) {
            return R.error(e.getMessage());
        }
    }
}