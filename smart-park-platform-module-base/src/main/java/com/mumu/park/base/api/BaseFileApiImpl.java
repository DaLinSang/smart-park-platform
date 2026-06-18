package com.mumu.park.base.api;

import com.mumu.park.base.service.BaseFileService;
import com.mumu.park.base.vo.BaseFilePersistableVO;
import com.mumu.park.base.vo.BaseFileVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BaseFileApiImpl implements BaseFileApi {

    private final BaseFileService baseFileService;

    @Override
    public R<List<BaseFilePersistableVO>> listByPage(int page, int size, String moduleId, String moduleDataId) {
        return R.success(baseFileService.listByPage(page, size, moduleId, moduleDataId));
    }

    @Override
    public R<BaseFilePersistableVO> getById(Long id) {
        BaseFilePersistableVO vo = baseFileService.getById(id);
        if (vo == null) {
            return R.error("附件不存在");
        }
        return R.success(vo);
    }

    @Override
    public R<List<BaseFilePersistableVO>> getByModule(String moduleId, String moduleDataId) {
        return R.success(baseFileService.getByModule(moduleId, moduleDataId));
    }

    @Override
    public R<BaseFilePersistableVO> create(BaseFileVO vo) {
        return R.success(baseFileService.create(vo));
    }

    @Override
    public R<List<BaseFilePersistableVO>> batchCreate(List<BaseFileVO> voList) {
        return R.success(baseFileService.batchCreate(voList));
    }

    @Override
    public R<BaseFilePersistableVO> update(Long id, BaseFileVO vo) {
        BaseFilePersistableVO result = baseFileService.update(id, vo);
        if (result == null) {
            return R.error("附件不存在");
        }
        return R.success(result);
    }

    @Override
    public R<Void> deleteById(Long id) {
        baseFileService.deleteById(id);
        return R.success();
    }
}