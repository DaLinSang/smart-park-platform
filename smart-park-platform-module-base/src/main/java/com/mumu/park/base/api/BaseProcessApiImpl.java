package com.mumu.park.base.api;

import com.mumu.park.base.service.BaseProcessService;
import com.mumu.park.base.vo.BaseProcessPersistableVO;
import com.mumu.park.base.vo.BaseProcessVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseProcessApiImpl implements BaseProcessApi {

    private final BaseProcessService baseProcessService;

    @Override
    public R<List<BaseProcessPersistableVO>> listByPage(int page, int size, String moduleDataId) {
        return R.success(baseProcessService.listByPage(page, size, moduleDataId));
    }

    @Override
    public R<List<BaseProcessPersistableVO>> getByModuleDataId(String moduleDataId) {
        List<BaseProcessPersistableVO> list = baseProcessService.getByModuleDataId(moduleDataId);
        if (list.isEmpty()) {
            return R.error("暂无审批记录");
        }
        return R.success(list);
    }

    @Override
    public R<BaseProcessPersistableVO> create(BaseProcessVO vo) {
        return R.success(baseProcessService.create(vo));
    }

    @Override
    public R<List<BaseProcessPersistableVO>> batchCreate(List<BaseProcessVO> voList) {
        return R.success(baseProcessService.batchCreate(voList));
    }

    @Override
    public R<Void> deleteById(Long id) {
        baseProcessService.deleteById(id);
        return R.success();
    }
}