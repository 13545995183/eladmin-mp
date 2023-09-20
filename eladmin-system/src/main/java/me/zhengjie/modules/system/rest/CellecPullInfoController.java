package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellecHaveInfo;
import me.zhengjie.modules.system.domain.CellecPullInfo;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellecPullInfoService;
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
@Api(tags = "藏品拉新用户接口")
@RequestMapping("/api/cellPullInfo")
public class CellecPullInfoController {
    @Autowired
    private CellecPullInfoService cellecPullInfoService;
    @GetMapping("/list")
    @ApiOperation("藏品拉新用户列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             String userId,
                             String searchInfo){
        QueryWrapper<CellecPullInfo> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotEmpty(userId)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecPullInfo::getPullUserId,userId))
                    .or().like(CellecPullInfo::getCoverPullUserId,userId);
        }
        if(StringUtils.isNotEmpty(searchInfo)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecPullInfo::getPullUserName,searchInfo)
                            .or().like(CellecPullInfo::getCoverPullUserName,searchInfo)
            );
        }
        Page<CellecPullInfo> page = new Page<>(pageNumber,pageSize);
        Page<CellecPullInfo> pageList = cellecPullInfoService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品拉新用户")
    public ResultEntity add(@RequestBody CellecPullInfo cellecPullInfo){
        cellecPullInfo.setCreateTime(new Date());
        cellecPullInfoService.save(cellecPullInfo);
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品拉新用户")
    public ResultEntity edit(@RequestBody CellecPullInfo cellecPullInfo){
        cellecPullInfo.setUpdateTime(new Date());
        cellecPullInfoService.saveOrUpdate(cellecPullInfo);
        return ResultEntity.success("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除藏品拉新用户")
    public ResultEntity delete (@RequestParam(value = "ids") String ids){
        cellecPullInfoService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
