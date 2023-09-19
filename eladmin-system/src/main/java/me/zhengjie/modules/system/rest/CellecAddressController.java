package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import me.zhengjie.domain.vo.QiniuQueryCriteria;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.domain.CellecAddress;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellecAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
@Api(tags = "藏品用户地址信息接口")
@RequestMapping("/api/cellAddressInfo")
public class CellecAddressController {
    @Autowired
    private CellecAddressService cellecAddressService;
    @ApiOperation("藏品用户地址列表")
    @GetMapping("/list")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             HttpRequest request){
        QueryWrapper<CellecAddress> queryWrapper= new QueryWrapper<>();
        Page<CellecAddress> page=new Page<>(pageNumber,pageSize);
        Page<CellecAddress> pageList=cellecAddressService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @ApiOperation("添加用户地址")
    @PostMapping("/add")
    public ResultEntity add(@RequestBody CellecAddress cellecAddress){
        cellecAddress.setCreateTime(new Date());
        cellecAddressService.save(cellecAddress);
        return ResultEntity.success("添加成功!");
    }
    @ApiOperation("修改用户地址")
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody CellecAddress cellecAddress){
        cellecAddress.setUpdateTime(new Date());
        cellecAddressService.updateById(cellecAddress);
        return ResultEntity.success("修改地址成功");
    }
    @ApiOperation("删除地址信息")
    @GetMapping("/delete")
    public ResultEntity delete(@RequestParam String ids){
        cellecAddressService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }

}
