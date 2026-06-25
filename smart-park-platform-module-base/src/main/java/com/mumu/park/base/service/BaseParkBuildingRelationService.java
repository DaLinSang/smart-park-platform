package com.mumu.park.base.service;

import com.mumu.park.base.vo.BaseParkBuildingRelationPersistableVO;
import com.mumu.park.base.vo.BaseParkBuildingRelationVO;

import java.util.List;

public interface BaseParkBuildingRelationService {

    /** 分页查询园区楼栋关系（可按园区ID筛选） */
    List<BaseParkBuildingRelationVO> listByPage(int page, int size, String parkId);

    /** 根据ID查询 */
    BaseParkBuildingRelationVO getById(Long id);

    /** 新增园区楼栋关系 */
    BaseParkBuildingRelationVO create(BaseParkBuildingRelationPersistableVO vo);

    /** 更新园区楼栋关系 */
    BaseParkBuildingRelationVO update(Long id, BaseParkBuildingRelationPersistableVO vo);

    /** 删除园区楼栋关系 */
    void deleteById(Long id);
}