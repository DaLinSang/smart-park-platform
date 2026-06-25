package com.mumu.park.base.service;

import com.mumu.park.base.entities.BaseParkEntity;
import com.mumu.park.base.vo.BaseParkPersistableVO;
import com.mumu.park.base.vo.BaseParkVO;

import java.util.List;

public interface BaseParkService {

    /** 分页查询园区列表 */
    List<BaseParkVO> listByPage(int page, int size);

    /** 查询所有园区 */
    List<BaseParkVO> getAllParks();

    /** 根据ID查询园区 */
    BaseParkVO getById(Long id);

    /** 根据用户ID查询所属园区列表（联查 t_user_park） */
    List<BaseParkVO> getUserParks(String userId);

    /** 新增园区 */
    BaseParkVO create(BaseParkPersistableVO vo);

    /** 根据ID更新园区 */
    BaseParkVO update(Long id, BaseParkPersistableVO vo);

    /** 删除园区（含业务校验，有楼栋则不能删） */
    void deleteById(Long id);

}
