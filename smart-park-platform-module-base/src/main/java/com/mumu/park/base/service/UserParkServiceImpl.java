package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mumu.park.base.converter.UserParkConverter;
import com.mumu.park.base.entities.BaseParkEntity;
import com.mumu.park.base.entities.UserParkEntity;
import com.mumu.park.base.repository.BaseParkRepository;
import com.mumu.park.base.repository.UserParkRepository;
import com.mumu.park.base.vo.UserParkPersistableVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户园区关联 Service 实现
 */
@Service
@RequiredArgsConstructor
public class UserParkServiceImpl extends ServiceImpl<UserParkRepository, UserParkEntity> implements UserParkService {

    private final UserParkConverter userParkConverter;
    private final BaseParkRepository baseParkRepository;

    @Override
    public List<UserParkEntity> getByUserId(String userId) {
        LambdaQueryWrapper<UserParkEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserParkEntity::getUserId, userId);
        return list(wrapper);
    }

    @Override
    public List<UserParkPersistableVO> getByUserIdWithParkName(String userId) {
        // 1. 查询用户关联列表
        List<UserParkEntity> entities = getByUserId(userId);
        if (entities.isEmpty()) {
            return List.of();
        }

        // 2. 收集所有 parkId 并转换为 Long 去查询对应园区
        List<Long> parkIds = entities.stream()
                .map(e -> Long.valueOf(e.getParkId()))
                .distinct()
                .toList();

        Map<Long, String> parkNameMap = baseParkRepository.selectBatchIds(parkIds)
                .stream()
                .collect(Collectors.toMap(BaseParkEntity::getId, BaseParkEntity::getParkName));

        // 3. 转换为 VO 并填充 parkName
        return entities.stream()
                .map(entity -> {
                    UserParkPersistableVO vo = userParkConverter.toPersistableVO(entity);
                    Long pkId = Long.valueOf(entity.getParkId());
                    vo.setParkName(parkNameMap.getOrDefault(pkId, null));
                    return vo;
                })
                .toList();
    }

    @Override
    public boolean deleteByUserId(String userId) {
        LambdaQueryWrapper<UserParkEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserParkEntity::getUserId, userId);
        // MyBatis Plus 逻辑删除：调用 remove 时会自动把 deleted 置为 1
        return remove(wrapper);
    }
}