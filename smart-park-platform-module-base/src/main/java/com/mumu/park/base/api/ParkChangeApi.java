package com.mumu.park.base.api;

import com.mumu.park.base.vo.ParkChangePersistableVO;
import com.mumu.park.base.vo.ParkChangeVO;
import com.mumu.park.common.util.R;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("base/parkChange")
public interface ParkChangeApi {

    /** 分页查询 */
    @GetMapping("/list")
    R<List<ParkChangePersistableVO>> listByPage(@RequestParam(defaultValue = "1") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(required = false) String applicantUserId,
                                                @RequestParam(required = false) Integer approvalStatus);

    /** 根据ID查询详情 */
    @GetMapping("/{id}")
    R<ParkChangePersistableVO> getById(@PathVariable Long id);

    /** 提交申请 */
    @PostMapping
    R<ParkChangePersistableVO> create(@RequestBody ParkChangeVO vo);

    /** 修改申请 */
    @PutMapping("/{id}")
    R<ParkChangePersistableVO> update(@PathVariable Long id, @RequestBody ParkChangeVO vo);

    /** 删除申请 */
    @DeleteMapping("/{id}")
    R<Void> deleteById(@PathVariable Long id);

    /** 审批通过 */
    @PutMapping("/approve/{id}")
    R<ParkChangePersistableVO> approve(@PathVariable Long id);

    /** 审批驳回 */
    @PutMapping("/reject/{id}")
    R<ParkChangePersistableVO> reject(@PathVariable Long id);
}