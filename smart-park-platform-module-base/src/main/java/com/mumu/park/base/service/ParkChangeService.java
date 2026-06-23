package com.mumu.park.base.service;

import com.mumu.park.base.vo.ParkChangePersistableVO;
import com.mumu.park.base.vo.ParkChangeVO;

import java.util.List;

public interface ParkChangeService {

    /** 分页查询 */
    List<ParkChangePersistableVO> listByPage(int page, int size, String applicantUserId, Integer approvalStatus);

    /** 根据ID查询 */
    ParkChangePersistableVO getById(Long id);

    /** 提交申请 */
    ParkChangePersistableVO create(ParkChangeVO vo);

    /** 修改申请（仅待审批状态可修改） */
    ParkChangePersistableVO update(Long id, ParkChangeVO vo);

    /** 删除 */
    void deleteById(Long id);

    /** 审批通过 */
    ParkChangePersistableVO approve(Long id);

    /** 审批驳回 */
    ParkChangePersistableVO reject(Long id);
}