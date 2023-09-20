package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellecIntegral;
import me.zhengjie.modules.system.domain.CellecIntegralRecord;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellecIntegralRecordService;
import me.zhengjie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Qian Sheng
 * @date 2023-9-20
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "藏品积分记录接口")
@RequestMapping("/api/cellIntegralRecord")
public class CellecIntegralRecordController {
    @Autowired
    private CellecIntegralRecordService cellecIntegralRecordService;
    @GetMapping("/list")
    @ApiOperation("藏品积分记录列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             String userId,
                             String searchInfo,
                             @RequestParam(value = "addReduce" ,defaultValue = "2")Integer addReduce,
                             @RequestParam(value = "descOrAsc",defaultValue = "desc")String descOrAsc){
        QueryWrapper<CellecIntegralRecord> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(userId)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecIntegralRecord::getUserId,userId));
        }
        if(StringUtils.isNotEmpty(searchInfo)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecIntegralRecord::getIntegralName,searchInfo));
        }
        if(addReduce!=2){
            queryWrapper.lambda().eq(CellecIntegralRecord::getAddReduce,addReduce);
        }
        if(descOrAsc.equals("desc")){
            queryWrapper.lambda().orderByDesc(CellecIntegralRecord::getCreateTime);
        }else if(descOrAsc.equals("asc")){
            queryWrapper.lambda().orderByAsc(CellecIntegralRecord::getCreateTime);
        }
        Page<CellecIntegralRecord> page=new Page<>(pageNumber,pageSize);
        Page<CellecIntegralRecord> pageList=cellecIntegralRecordService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品记录积分")
    public ResultEntity add(@RequestBody CellecIntegralRecord cellecIntegralRecord){
        cellecIntegralRecord.setCreateTime(new Date());
        cellecIntegralRecordService.save(cellecIntegralRecord);
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品记录积分")
    public ResultEntity edit(@RequestBody CellecIntegralRecord cellecIntegralRecord){
        cellecIntegralRecord.setUpdateTime(new Date());
        cellecIntegralRecordService.saveOrUpdate(cellecIntegralRecord);
        return ResultEntity.success("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除藏品记录积分")
    public ResultEntity delete (@RequestParam(value = "ids") String ids){
        cellecIntegralRecordService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
