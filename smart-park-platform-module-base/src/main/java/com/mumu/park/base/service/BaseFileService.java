package com.mumu.park.base.service;

import com.mumu.park.base.vo.BaseFilePersistableVO;
import com.mumu.park.base.vo.BaseFileVO;

import java.util.List;

public interface BaseFileService {

    /** 分页查询 */
    List<BaseFilePersistableVO> listByPage(int page, int size, String moduleId, String moduleDataId);

    /** 根据ID查询 */
    BaseFilePersistableVO getById(Long id);

    /** 根据 moduleId+moduleDataId 批量查询（查某个业务的所有附件） */
    List<BaseFilePersistableVO> getByModule(String moduleId, String moduleDataId);

    /** 新增 */
    BaseFilePersistableVO create(BaseFileVO vo);

    /** 批量新增 */
    List<BaseFilePersistableVO> batchCreate(List<BaseFileVO> voList);

    /** 更新 */
    BaseFilePersistableVO update(Long id, BaseFileVO vo);

    /** 删除 */
    void deleteById(Long id);
}