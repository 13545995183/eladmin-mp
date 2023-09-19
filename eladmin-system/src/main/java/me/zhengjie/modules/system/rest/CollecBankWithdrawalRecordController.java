package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CollecBankWithdrawalRecord;
import me.zhengjie.modules.system.service.CollecBankWithdrawalRecordService;
import org.elasticsearch.action.ValidateActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
* qiansheng
 * 2023-9-19
* */
@RestController
@RequiredArgsConstructor
@Api(tags = "藏品用户交易记录")
@RequestMapping("/api/cellBankWithdrawalRecord")
public class CollecBankWithdrawalRecordController {
    @Autowired
    private CollecBankWithdrawalRecordService collecBankWithdrawalRecordService;
    @ApiOperation("交易记录集合")
    @GetMapping("/list")
    public ResponseEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                               @RequestParam(value = "descOrAsc",defaultValue = "0") Integer descOrAsc){
        QueryWrapper<CollecBankWithdrawalRecord> queryWrapper=new QueryWrapper<CollecBankWithdrawalRecord>();
        if (descOrAsc==0){
            queryWrapper.lambda().orderByDesc(CollecBankWithdrawalRecord::getCreateTime);
        } else {
            queryWrapper.lambda().orderByAsc(CollecBankWithdrawalRecord::getCreateTime);
        }
        Page<CollecBankWithdrawalRecord> page=new Page<>(pageNumber,pageSize);
        Page<CollecBankWithdrawalRecord> pageList=collecBankWithdrawalRecordService.page(page,queryWrapper);
        return ResponseEntity.ok(pageList);
    }
    @ApiOperation("添加")
    @PostMapping("/add")
    public ResponseEntity add(@RequestBody CollecBankWithdrawalRecord collecBankWithdrawalRecord){
        collecBankWithdrawalRecord.setCreateTime(new Date());
        collecBankWithdrawalRecordService.save(collecBankWithdrawalRecord);
        return ResponseEntity.ok("添加成功");
    }
    @ApiOperation("修改")
    @PostMapping("/edit")
    public ResponseEntity edit(@RequestBody CollecBankWithdrawalRecord collecBankWithdrawalRecord){
        collecBankWithdrawalRecord.setUpdateTime(new Date());
        collecBankWithdrawalRecordService.updateById(collecBankWithdrawalRecord);
        return ResponseEntity.ok("修改成功");
    }
    @ApiOperation("删除")
    @PostMapping("/delete")
    public ResponseEntity delete(@RequestParam(value = "ids")String ids){
        collecBankWithdrawalRecordService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok("删除成功");
    }
}
