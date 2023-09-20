package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellecIntegral;
import me.zhengjie.modules.system.domain.CellecIntegralActivity;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellecIntegralActivityService;
import me.zhengjie.utils.StringUtils;
import org.elasticsearch.common.recycler.Recycler;
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
@Api(tags = "藏品积分活动接口")
@RequestMapping("/api/cellIntegralActivity")
public class CellecIntegralActivityController {
    @Autowired
    private CellecIntegralActivityService cellecIntegralActivityService;
    @GetMapping("/list")
    @ApiOperation("藏品积分列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "addReduce" , defaultValue = "2") Integer addReduce,
                             String searchInfo){
        QueryWrapper<CellecIntegralActivity> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(searchInfo)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecIntegralActivity::getName,searchInfo));
        }
        if(addReduce!=2){
            queryWrapper.lambda().eq(CellecIntegralActivity::getAddReduce,addReduce);
        }
        Page<CellecIntegralActivity> page=new Page<>(pageNumber,pageSize);
        Page<CellecIntegralActivity> pageList=cellecIntegralActivityService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品积分")
    public ResultEntity add(@RequestBody CellecIntegralActivity cellecIntegralActivity){
        cellecIntegralActivity.setCreateTime(new Date());
        cellecIntegralActivityService.save(cellecIntegralActivity);
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品积分")
    public ResultEntity edit(@RequestBody CellecIntegralActivity cellecIntegralActivity){
        cellecIntegralActivity.setUpdateTime(new Date());
        cellecIntegralActivityService.saveOrUpdate(cellecIntegralActivity);
        return ResultEntity.success("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除藏品积分")
    public ResultEntity delete (@RequestParam(value = "ids") String ids){
        cellecIntegralActivityService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
