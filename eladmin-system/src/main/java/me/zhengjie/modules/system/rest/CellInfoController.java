package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellInfoService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Qian Sheng
 * @date 2023-9-13
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "藏品信息接口")
@RequestMapping("/api/cellInfo")
public class CellInfoController  {
    @Autowired
    private CellInfoService cellInfoService;
    @ApiOperation("查询所有藏品信息接口")
    @GetMapping("/list")
    public ResultEntity cellInfoList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                     @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                     CellInfo cellInfo,
                                     HttpRequest request){
        Page<CellInfo> page=new Page<>(pageNumber,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        Page<CellInfo> pageList=cellInfoService.page(page,queryWrapper);
        ResponseEntity.status(200);
        return ResultEntity.success(pageList);
    }
    @ApiOperation("添加藏品信息")
    @PostMapping("/add")
    public ResultEntity add(@RequestBody CellInfo collInfo){
        collInfo.setCreateTime(new Date());
        cellInfoService.save(collInfo);
        return ResultEntity.success("添加成功");
    }
    @ApiOperation("修改藏品信息")
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody CellInfo collInfo){
        collInfo.setCreateTime(new Date());
        cellInfoService.save(collInfo);
        return ResultEntity.success("修改成功");
    }
    @ApiOperation("删除藏品信息")
    @GetMapping("/deleteByIds")
    public ResultEntity deleteById(@RequestParam String ids){
        cellInfoService.removeByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
