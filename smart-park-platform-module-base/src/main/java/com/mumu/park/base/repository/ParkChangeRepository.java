package com.mumu.park.base.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mumu.park.base.entities.ParkChangeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ParkChangeRepository extends BaseMapper<ParkChangeEntity> {

    /** 根据申请人ID查询 */
    @Select("SELECT * FROM t_park_change WHERE applicant_user_id = #{applicantUserId} AND deleted = 0 ORDER BY create_time DESC")
    List<ParkChangeEntity> getByApplicantUserId(String applicantUserId);

    /** 根据审批状态查询 */
    @Select("SELECT * FROM t_park_change WHERE approval_status = #{status} AND deleted = 0 ORDER BY create_time DESC")
    List<ParkChangeEntity> getByApprovalStatus(Integer status);
}