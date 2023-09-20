package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellOrderInfo;
import me.zhengjie.modules.system.domain.CellecHaveInfo;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellecHaveInfoService;
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
@Api(tags = "藏品用户拥有接口")
@RequestMapping("/api/cellHaveInfo")
public class CellecHaveInfoController {
    @Autowired
    private CellecHaveInfoService cellecHaveInfoService;
    @GetMapping("/list")
    @ApiOperation("藏品用户拥有列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             String userId,
                             String searchInfo){
        QueryWrapper<CellecHaveInfo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(searchInfo)){
            //多条件查询
            queryWrapper.lambda().and(
                    item -> item.or().eq(CellecHaveInfo::getCollecId,searchInfo)
                            .or().like(CellecHaveInfo::getName,searchInfo)
            );
        }
        if(StringUtils.isNotEmpty(userId)){
            queryWrapper.lambda().like(CellecHaveInfo::getUserId,userId);
        }
        Page<CellecHaveInfo> page = new Page<>(pageNumber,pageSize);
        Page<CellecHaveInfo> pageList = cellecHaveInfoService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品用户拥有")
    public ResultEntity add(@RequestBody CellecHaveInfo cellecHaveInfo){
        cellecHaveInfo.setCreateTime(new Date());
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品用户拥有")
    public ResultEntity edit(@RequestBody CellecHaveInfo cellecHaveInfo){
        cellecHaveInfo.setUpdateTime(new Date());
        cellecHaveInfoService.saveOrUpdate(cellecHaveInfo);
        return ResultEntity.success("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除藏品用户拥有")
    public ResultEntity delete (@RequestParam(value = "ids") String ids){
        cellecHaveInfoService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
