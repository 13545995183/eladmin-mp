package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.*;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.*;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Qian Sheng
 * @date 2023-9-13
 */
@RestController
@RequiredArgsConstructor
@Api(tags = "小程序接口")
@RequestMapping("/api/appApi")
public class AppApiController {
    @Autowired
    private DeptService deptService;
    @Autowired
    private UserService userService;
    @Autowired
    private CellUserService cellUserService;
    @Autowired
    private CellInfoService cellInfoService;
    @Autowired
    private CellecAddressService cellecAddressService;

    @ApiOperation("查询所有部门")
    @GetMapping("/getDeptList")
    public ResultEntity getDeptList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page page=new Page(pageNumber,pageSize);
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        Page pageList=deptService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @ApiOperation("查询所有角色")
    @GetMapping("/getUserList")
    public ResultEntity getUserList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                    @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page page=new Page(pageNumber,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Page pageList=userService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @ApiOperation("查询所有藏品用户角色")
    @GetMapping("/getCellUserList")
    public ResultEntity getCellUserList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                          String CellUserPhone){
        Page page=new Page(pageNumber,pageSize);
        QueryWrapper<CellUser> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotEmpty(CellUserPhone)){
            queryWrapper.lambda().eq(CellUser::getPhone,CellUserPhone);
        }
        Page pageList=cellUserService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }

    @ApiOperation("查询所有藏品信息接口")
    @GetMapping("/cellInfoList")
    public ResultEntity cellInfoList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                      @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page<CellInfo> page=new Page<>(pageNumber,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        Page<CellInfo> pageList=cellInfoService.page(page,queryWrapper);
        ResponseEntity.status(200);
        return ResultEntity.success(pageList.getRecords());
    }

    @ApiOperation("藏品用户地址列表")
    @GetMapping("/collecAddressInfoByUserId")
    public ResultEntity cellAddressList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                               @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                               @RequestParam String userId){

        QueryWrapper<CellecAddress> queryWrapper= new QueryWrapper<>();
        queryWrapper.lambda().eq(CellecAddress::getUserId,userId);
        Page<CellecAddress> page=new Page<>(pageNumber,pageSize);
        Page<CellecAddress> pageList=cellecAddressService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @ApiOperation("修改用户地址列表")
    @PostMapping("/updateAddress")
    public ResultEntity updateAddress(@RequestBody CellecAddress cellecAddress){
        cellecAddress.setUpdateTime(new Date());
        cellecAddressService.updateById(cellecAddress);
        return ResultEntity.success("修改地址成功");
    }
    @ApiOperation("删除地址信息")
    @GetMapping("/deleteAddressByIds")
    public ResultEntity deleteById(@RequestParam String ids){
        cellecAddressService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
    @Autowired
    private CellecBankInfoService cellecBankInfoService;
    @ApiOperation("用户账户信息")
    @GetMapping("/cellecBankInfoByUserId")
    public ResultEntity cellecBankInfoByUserId(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                                       @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                                       @RequestParam String userId){
        QueryWrapper<CellecBankInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(CellecBankInfo::getUserId,userId);
        Page<CellecBankInfo> page=new Page<>(pageNumber,pageSize);
        Page<CellecBankInfo> pageList=cellecBankInfoService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @ApiOperation("修改用户账户信息")
    @PostMapping("/updateCellecBankInfo")
    public  ResultEntity updateCellecBankInfo(@RequestBody CellecBankInfo cellecBankInfo){
        cellecBankInfo.setUpdateTime(new Date());
        cellecBankInfoService.updateById(cellecBankInfo);
        return ResultEntity.success("修改成功");
    }

    @Autowired
    private CollecBankWithdrawalRecordService collecBankWithdrawalRecordService;
    @ApiOperation("交易信息列表")
    @GetMapping("/cellBankWithdrawalRecordList")
    public ResultEntity cellBankWithdrawalRecordList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                     @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                     @RequestParam String userId){
        QueryWrapper<CollecBankWithdrawalRecord> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(CollecBankWithdrawalRecord::getUserId,userId).orderByDesc(CollecBankWithdrawalRecord::getCreateTime);
        Page<CollecBankWithdrawalRecord> page=new Page<>(pageNumber,pageSize);
        Page<CollecBankWithdrawalRecord> pageList=collecBankWithdrawalRecordService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
}

