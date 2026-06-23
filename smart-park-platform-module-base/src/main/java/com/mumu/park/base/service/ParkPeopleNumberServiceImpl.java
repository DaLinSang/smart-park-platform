package com.mumu.park.base.service;

import com.mumu.park.base.vo.ParkPeopleNumberVO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 在园人数 Service - Mock实现
 *
 * 当前返回模拟数据，待对接硬件设备（摄像头、道闸）后替换为真实数据源。
 * 公式：在园人数 = (进门人数 − 出门人数) + (在场车辆数)
 */
@Service
public class ParkPeopleNumberServiceImpl implements ParkPeopleNumberService {

    /** 模拟不同园区的在园人数（按parkId缓存，每次返回变化的数据） */
    private static final Map<String, Integer> CACHE = new ConcurrentHashMap<>();

    @Override
    public ParkPeopleNumberVO getByParkId(String parkId) {
        // 生成波动数据，模拟实时变化
        int entered = ThreadLocalRandom.current().nextInt(500, 2000);
        int exited = ThreadLocalRandom.current().nextInt(200, entered);
        int vehicles = ThreadLocalRandom.current().nextInt(50, 300);
        int total = (entered - exited) + vehicles;

        return new ParkPeopleNumberVO(parkId, total, entered, exited, vehicles);
    }
}