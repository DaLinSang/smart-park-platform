package com.mumu.park.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mumu.park.base.converter.BaseParkConverter;
import com.mumu.park.base.entities.BaseBuildingEntity;
import com.mumu.park.base.entities.BaseParkEntity;
import com.mumu.park.base.repository.BaseBuildingRepository;
import com.mumu.park.base.repository.BaseParkRepository;
import com.mumu.park.base.vo.BaseParkPersistableVO;
import com.mumu.park.base.vo.BaseParkVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;


@Service
@RequiredArgsConstructor
public class BaseParkServiceImpl implements BaseParkService {

    private final BaseParkRepository baseParkRepository;
    private final BaseBuildingRepository baseBuildingRepository;
    private final BaseParkConverter baseParkConverter;
    private final UserParkService userParkService;

    @Override
    public List<BaseParkVO> listByPage(int page, int size) {
        // MyBatis-Plus 分页查询（IPage 是 MyBatis-Plus 的分页接口）
        // 这里先用全部查询，分页也可以在 Controller 层处理
        List<BaseParkEntity> entityList = baseParkRepository.selectList(null);
        // 注意，这块map（）里面要求的是传入一个函数
        // 这个baseParkConverter::toVO和pojo -> baseParkConverter.toVO(pojo)等价
        // 但是和baseParkConverter.toVO(pojo)不等价，因为baseParkConverter.toVO(pojo)返回的是一个VO对象，不是函数

        // 具体函数理解
        // stream()把普通计划转成java流式对象，开启流式批量处理
        // map() 流里的转换操作，遍历集合里面的每一个元素，使用里面的函数逐个加工，即循环每一个实体对象，调用toVO方法，把实体-->VO
        // toList()把处理完的流式数据，重新转回普通List集合
        // 最终返回VO集合
        return entityList.stream()
                .map(baseParkConverter::toVO)
                .toList();

    }

    @Override
    public List<BaseParkVO> getAllParks() {
        return baseParkRepository.selectList(null).stream().map(baseParkConverter::toVO).toList();
    }

    @Override
    public BaseParkVO getById(Long id) {
        BaseParkEntity entity = baseParkRepository.selectById(id);
        if (entity != null){
            return baseParkConverter.toVO(entity);
        }
        return null;
    }

    @Override
    public List<BaseParkVO> getUserParks(String userId) {
        // 1. 查询用户关联的园区ID列表
        List<com.mumu.park.base.entities.UserParkEntity> userParks = userParkService.getByUserId(userId);
        if (userParks.isEmpty()) {
            return Collections.emptyList();
        }
        // 2. 提取parkId并去重
        List<Long> parkIds = userParks.stream()
                .map(up -> Long.valueOf(up.getParkId()))
                .distinct()
                .toList();
        // 3. 根据ID列表查询园区
        List<BaseParkEntity> entities = baseParkRepository.selectBatchIds(parkIds);
        return entities.stream()
                .map(baseParkConverter::toVO)
                .toList();
    }

    @Override
    public BaseParkVO create(BaseParkPersistableVO vo) {
        //先将前端传来的VO数据转换成entity实体
        BaseParkEntity entity = baseParkConverter.toEntity(vo);
        //在数据库中根据实体插入数据
        baseParkRepository.insert(entity);
        //再将新增实体转换回VO数据返回给前端
        return baseParkConverter.toVO(entity);
    }

    @Override
    public BaseParkVO update(Long id, BaseParkPersistableVO vo) {
        BaseParkEntity entity = baseParkConverter.toEntity(vo);
        // 手动给实体设置主键id
        entity.setId(id);
        baseParkRepository.updateById(entity);
        return getById(id);
    }

    @Override
    public void deleteById(Long id) {
        //涉及到业务逻辑：删除之前需要检查园区下是否有楼栋

        // Wrapper是什么：想象成一个收纳盒，专门用来装查询筛选条件的，往里面加一条一条规则，MP会自动把这些规则翻译成SQL的WHERE条件
        // LambdaQueryWrapper<BaseBuildingEntity>是带有Lambda语法的条件盒子，优点是不用手写数据库字段名字符串，直接靠实体类BaseBuildingEntity的get方法引用
        LambdaQueryWrapper<BaseBuildingEntity> wrapper = new LambdaQueryWrapper<>();

        // eq 即为等于，getParkId()是楼栋实体类里面的parkId字段的get方法，第二个id是接口传进来的待删除园区的id
        // 整句话可以直接翻译为 WHERE park_id = #{园区id}
        wrapper.eq(BaseBuildingEntity::getParkId, String.valueOf(id));

        // 现在使用wrapper，按条件统计数据条数
        // 相当于一层SQL的嵌套，完整执行的SQL是：
        // -- 查询：属于当前园区的楼栋一共有多少条
        // SELECT COUNT(*) FROM base_building WHERE park_id = ?;
        Long buildingCount = baseBuildingRepository.selectCount(wrapper);
        if (buildingCount > 0){
            throw new RuntimeException("请先删除该园区下的所有楼栋");
        }
        baseParkRepository.deleteById(id);

    }
}
