package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.ParkChangeConverter;
import com.mumu.park.base.entities.ParkChangeEntity;
import com.mumu.park.base.entities.UserParkEntity;
import com.mumu.park.base.repository.ParkChangeRepository;
import com.mumu.park.base.repository.UserParkRepository;
import com.mumu.park.base.vo.BaseProcessVO;
import com.mumu.park.base.vo.ParkChangePersistableVO;
import com.mumu.park.base.vo.ParkChangeVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParkChangeServiceImpl implements ParkChangeService {

    private final ParkChangeRepository parkChangeRepository;
    private final ParkChangeConverter parkChangeConverter;
    private final BaseProcessService baseProcessService;
    private final UserParkRepository userParkRepository;

    @Override
    public List<ParkChangePersistableVO> listByPage(int page, int size, String applicantUserId, Integer approvalStatus) {
        LambdaQueryWrapper<ParkChangeEntity> wrapper = new LambdaQueryWrapper<>();
        if (applicantUserId != null && !applicantUserId.isEmpty()) {
            wrapper.eq(ParkChangeEntity::getApplicantUserId, applicantUserId);
        }
        if (approvalStatus != null) {
            wrapper.eq(ParkChangeEntity::getApprovalStatus, approvalStatus);
        }
        wrapper.orderByDesc(ParkChangeEntity::getCreateTime);
        return parkChangeRepository.selectList(wrapper)
                .stream()
                .map(parkChangeConverter::toPersistableVO)
                .toList();
    }

    @Override
    public ParkChangePersistableVO getById(Long id) {
        ParkChangeEntity entity = parkChangeRepository.selectById(id);
        return entity != null ? parkChangeConverter.toPersistableVO(entity) : null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkChangePersistableVO create(ParkChangeVO vo) {
        ParkChangeEntity entity = parkChangeConverter.toEntity(vo);
        entity.setApprovalStatus(0); // 初始状态：待审批
        parkChangeRepository.insert(entity);
        return parkChangeConverter.toPersistableVO(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkChangePersistableVO update(Long id, ParkChangeVO vo) {
        ParkChangeEntity entity = parkChangeRepository.selectById(id);
        if (entity == null) {
            throw new RuntimeException("申请记录不存在");
        }
        if (entity.getApprovalStatus() != 0) {
            throw new RuntimeException("仅待审批状态的申请可修改");
        }

        // 用Converter映射更新字段（保留id）
        ParkChangeEntity updated = parkChangeConverter.toEntity(id, vo);
        // 保留原始审批状态
        updated.setApprovalStatus(entity.getApprovalStatus());
        parkChangeRepository.updateById(updated);
        return parkChangeConverter.toPersistableVO(parkChangeRepository.selectById(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        parkChangeRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkChangePersistableVO approve(Long id) {
        ParkChangeEntity entity = parkChangeRepository.selectById(id);
        if (entity == null) {
            throw new RuntimeException("申请记录不存在");
        }
        if (entity.getApprovalStatus() != 0) {
            throw new RuntimeException("该申请已被处理，无法重复审批");
        }

        // 1. 更新审批状态为"已审批"
        entity.setApprovalStatus(2);
        entity.setApprovedParkId(entity.getApplyingParkId());
        parkChangeRepository.updateById(entity);

        // 2. 创建审批通过的处理记录
        BaseProcessVO processVO = new BaseProcessVO();
        processVO.setModuleDataId(String.valueOf(id));
        processVO.setAction(1);
        processVO.setActionName("审批通过");
        processVO.setTenantId(entity.getTenantId());
        baseProcessService.create(processVO);

        // 3. 更新用户园区归属（删除旧的，插入新的）
        LambdaQueryWrapper<UserParkEntity> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(UserParkEntity::getUserId, entity.getApplicantUserId());
        userParkRepository.delete(userWrapper);
        UserParkEntity userPark = new UserParkEntity();
        userPark.setUserId(entity.getApplicantUserId());
        userPark.setParkId(entity.getApprovedParkId());
        userPark.setTenantId(entity.getTenantId());
        userParkRepository.insert(userPark);

        return parkChangeConverter.toPersistableVO(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ParkChangePersistableVO reject(Long id) {
        ParkChangeEntity entity = parkChangeRepository.selectById(id);
        if (entity == null) {
            throw new RuntimeException("申请记录不存在");
        }
        if (entity.getApprovalStatus() != 0) {
            throw new RuntimeException("该申请已被处理，无法重复审批");
        }

        // 1. 更新审批状态为"已驳回"
        entity.setApprovalStatus(3);
        parkChangeRepository.updateById(entity);

        // 2. 创建审批驳回的处理记录
        BaseProcessVO processVO = new BaseProcessVO();
        processVO.setModuleDataId(String.valueOf(id));
        processVO.setAction(2);
        processVO.setActionName("审批驳回");
        processVO.setTenantId(entity.getTenantId());
        baseProcessService.create(processVO);

        return parkChangeConverter.toPersistableVO(entity);
    }
}