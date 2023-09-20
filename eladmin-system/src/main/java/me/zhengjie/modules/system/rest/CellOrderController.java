package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellOrderInfo;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellOrderInfoService;
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
@Api(tags = "藏品订单信息接口")
@RequestMapping("/api/cellOrderInfo")
public class CellOrderController {
    @Autowired
    private CellOrderInfoService cellOrderInfoService;
    @GetMapping("/list")
    @ApiOperation("藏品订单列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             String userId,
                             String searchInfo){
        QueryWrapper<CellOrderInfo> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isEmpty(searchInfo)){
            return ResultEntity.success("无数据");
        }
        if(StringUtils.isNotEmpty(userId)){
            queryWrapper.lambda().like(CellOrderInfo::getUserId,userId);
        }
        //多条件查询
        queryWrapper.lambda().and(
                item -> item.or().eq(CellOrderInfo::getCollId,searchInfo)
                        .or().like(CellOrderInfo::getName,searchInfo)
        );
        Page<CellOrderInfo> page=new Page<>(pageNumber,pageSize);
        Page<CellOrderInfo> pageList=cellOrderInfoService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品订单")
    public ResultEntity add(@RequestBody CellOrderInfo cellOrderInfo){
        cellOrderInfo.setCreateTime(new Date());
        cellOrderInfoService.save(cellOrderInfo);
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品订单")
    public ResultEntity edit(@RequestBody CellOrderInfo cellOrderInfo){
        cellOrderInfo.setUpdateTime(new Date());
        cellOrderInfoService.saveOrUpdate(cellOrderInfo);
        return ResultEntity.success("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除藏品订单")
    public ResultEntity delete (@RequestParam(value = "ids") String ids){
        cellOrderInfoService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
