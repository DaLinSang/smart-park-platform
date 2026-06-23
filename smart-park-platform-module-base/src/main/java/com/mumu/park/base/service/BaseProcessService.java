package com.mumu.park.base.service;

import com.mumu.park.base.vo.BaseProcessPersistableVO;
import com.mumu.park.base.vo.BaseProcessVO;

import java.util.List;

public interface BaseProcessService {

    /** 分页查询 */
    List<BaseProcessPersistableVO> listByPage(int page, int size, String moduleDataId);

    /** 根据业务数据ID查审批进度（核心接口） */
    List<BaseProcessPersistableVO> getByModuleDataId(String moduleDataId);

    /** 新增单条 */
    BaseProcessPersistableVO create(BaseProcessVO vo);

    /** 批量新增（含处理人） */
    List<BaseProcessPersistableVO> batchCreate(List<BaseProcessVO> voList);

    /** 删除 */
    void deleteById(Long id);
}