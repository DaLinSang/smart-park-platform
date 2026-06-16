package com.mumu.park.base.api;

import com.mumu.park.base.converter.UserParkConverter;
import com.mumu.park.base.entities.UserParkEntity;
import com.mumu.park.base.service.UserParkService;
import com.mumu.park.base.vo.UserParkPersistableVO;
import com.mumu.park.base.vo.UserParkVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserParkApiImpl implements UserParkApi {

    private final UserParkService userParkService;
    private final UserParkConverter userParkConverter;

    @Override
    public R<List<UserParkPersistableVO>> list() {
        List<UserParkEntity> entityList = userParkService.list();
        List<UserParkPersistableVO> voList = entityList.stream()
                .map(userParkConverter::toPersistableVO)
                .toList();
        return R.success(voList);
    }

    @Override
    public R<UserParkPersistableVO> getById(Long id) {
        UserParkEntity entity = userParkService.getById(id);
        if (entity == null) {
            return R.error("记录不存在");
        }
        return R.success(userParkConverter.toPersistableVO(entity));
    }

    @Override
    public R<List<UserParkPersistableVO>> getByUserId(String userId) {
        List<UserParkEntity> entityList = userParkService.getByUserId(userId);
        List<UserParkPersistableVO> voList = entityList.stream()
                .map(userParkConverter::toPersistableVO)
                .toList();
        return R.success(voList);
    }

    @Override
    public R<Long> add(UserParkVO vo) {
        UserParkEntity entity = userParkConverter.toEntity(vo);
        userParkService.save(entity);
        return R.success(entity.getId());
    }

    @Override
    public R<Boolean> update(Long id, UserParkVO vo) {
        UserParkEntity entity = userParkConverter.toEntity(id, vo);
        return R.success(userParkService.updateById(entity));
    }

    @Override
    public R<Boolean> delete(Long id) {
        return R.success(userParkService.removeById(id));
    }

    @Override
    public R<Boolean> deleteByUserId(String userId) {
        return R.success(userParkService.deleteByUserId(userId));
    }
}