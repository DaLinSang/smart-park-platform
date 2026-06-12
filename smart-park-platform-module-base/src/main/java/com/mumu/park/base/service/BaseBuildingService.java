package com.mumu.park.base.service;

import com.mumu.park.base.entities.BaseBuildingEntity;
import com.mumu.park.base.vo.BaseBuildingPersistableVO;
import com.mumu.park.base.vo.BaseBuildingVO;

import java.util.List;

public interface BaseBuildingService {

    /** 分页查询楼栋（可按园区筛选） */
    List<BaseBuildingVO> listByPage(int page, int size, Long parkId);

    /** 根据ID查询 */
    BaseBuildingVO getById(Long id);

    /** 新增楼栋 */
    BaseBuildingVO create(BaseBuildingPersistableVO vo);

    /** 更新楼栋 */
    BaseBuildingVO update(Long id, BaseBuildingPersistableVO vo);

    /** 删除楼栋 */
    void deleteById(Long id);

}
