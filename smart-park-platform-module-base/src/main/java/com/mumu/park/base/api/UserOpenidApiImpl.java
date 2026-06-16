package com.mumu.park.base.api;

import com.mumu.park.base.converter.UserOpenidConverter;
import com.mumu.park.base.entities.UserOpenidEntity;
import com.mumu.park.base.service.UserOpenidService;
import com.mumu.park.base.vo.UserOpenidPersistableVO;
import com.mumu.park.base.vo.UserOpenidVO;
import com.mumu.park.common.util.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserOpenidApiImpl implements UserOpenidApi {

    private final UserOpenidService userOpenidService;
    private final UserOpenidConverter userOpenidConverter;

    @Override
    public R<List<UserOpenidPersistableVO>> list() {
        List<UserOpenidEntity> entityList = userOpenidService.list();
        List<UserOpenidPersistableVO> voList = entityList.stream()
                .map(userOpenidConverter::toPersistableVO)
                .toList();
        return R.success(voList);
    }

    @Override
    public R<UserOpenidPersistableVO> getById(Long id) {
        UserOpenidEntity entity = userOpenidService.getById(id);
        if (entity == null) {
            return R.error("记录不存在");
        }
        return R.success(userOpenidConverter.toPersistableVO(entity));
    }

    @Override
    public R<UserOpenidPersistableVO> getByAccountId(String accountId) {
        UserOpenidEntity entity = userOpenidService.getByAccountId(accountId);
        if (entity == null) {
            return R.error("该用户未绑定OpenID");
        }
        return R.success(userOpenidConverter.toPersistableVO(entity));
    }

    @Override
    public R<Long> add(UserOpenidVO vo) {
        UserOpenidEntity entity = userOpenidConverter.toEntity(vo);
        userOpenidService.save(entity);
        return R.success(entity.getId());
    }

    @Override
    public R<Boolean> update(Long id, UserOpenidVO vo) {
        UserOpenidEntity entity = userOpenidConverter.toEntity(id, vo);
        return R.success(userOpenidService.updateById(entity));
    }

    @Override
    public R<Boolean> delete(Long id) {
        return R.success(userOpenidService.removeById(id));
    }
}