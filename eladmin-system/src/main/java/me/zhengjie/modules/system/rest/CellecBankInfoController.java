package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellecBankInfo;
import me.zhengjie.modules.system.service.CellecBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

/**
 * @author Qian Sheng
 * @date 2023-9-13
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "藏品用户账户接口")
@RequestMapping("/api/cellBankInfo")
public class CellecBankInfoController {
    @Autowired
    private CellecBankInfoService cellecBankInfoService;
    @ApiOperation("账户信息集合信息")
    @GetMapping("/list")
    public ResponseEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                               @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                               String phone,
                               String userId,
                               String name){
        Page<CellecBankInfo> page = new Page<CellecBankInfo>(pageNumber,pageSize);
        QueryWrapper<CellecBankInfo> queryWrapper = new QueryWrapper<>();
        if(!phone.isEmpty()){
            queryWrapper.lambda().eq(CellecBankInfo::getPhone,phone);
        }
        if(!userId.isEmpty()){
            queryWrapper.lambda().eq(CellecBankInfo::getUserId,userId);
        }
        if(!name.isEmpty()){
            queryWrapper.lambda().eq(CellecBankInfo::getName,name);
        }
        Page<CellecBankInfo> pageList =cellecBankInfoService.page(page,queryWrapper);
        return ResponseEntity.ok(pageList);
    }
    @PostMapping("/add")
    @ApiOperation("添加账户信息")
    public ResponseEntity add(@RequestBody CellecBankInfo cellecBankInfo){
        cellecBankInfo.setCreateTime(new Date());
        cellecBankInfoService.save(cellecBankInfo);
        return ResponseEntity.ok("成功添加");
    }
    @PostMapping("/edit")
    @ApiOperation("修改账户信息")
    public ResponseEntity edit(@RequestBody CellecBankInfo cellecBankInfo){
        cellecBankInfo.setUpdateTime(new Date());
        cellecBankInfoService.updateById(cellecBankInfo);
        return ResponseEntity.ok("修改账户信息成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除账户信息")
    public ResponseEntity edit(@RequestParam String ids){
        cellecBankInfoService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok("删除成功");
    }
}
