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
import net.dreamlu.mica.core.result.R;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
        List<Map> cellList=cellInfoService.queryList();
        return ResultEntity.success(cellList);
    }

    @ApiOperation("藏品用户地址列表")
    @GetMapping("/collecAddressInfoByUserId")
    public ResultEntity cellAddressList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                        @RequestParam String userId){
        List<CellecAddress> addressList=cellecAddressService.listByUserId(userId);
        return ResultEntity.success(addressList);
    }
    @ApiOperation("修改用户地址列表")
    @PostMapping("/updateAddress")
    public ResultEntity updateAddress(@RequestBody CellecAddress cellecAddress){
        cellecAddress.setUpdateTime(new Date());
        cellecAddressService.saveOrUpdate(cellecAddress);
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
        cellecBankInfoService.saveOrUpdate(cellecBankInfo);
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
        queryWrapper.lambda().eq(CollecBankWithdrawalRecord::getUserId,userId)
                .orderByDesc(CollecBankWithdrawalRecord::getCreateTime);
        Page<CollecBankWithdrawalRecord> page=new Page<>(pageNumber,pageSize);
        Page<CollecBankWithdrawalRecord> pageList=collecBankWithdrawalRecordService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords());
    }
    @ApiOperation("交易信息详情")
    @GetMapping("/cellBankWithdrawalRecordById")
    public ResultEntity cellBankWithdrawalRecordById(@RequestParam("id")String id){
        return ResultEntity.success(collecBankWithdrawalRecordService.getById(id));
    }

    @Autowired
    private CellOrderInfoService cellOrderInfoService;
    @ApiOperation("订单信息列表-根据用户查询订单")
    @GetMapping("/cellOrderInfoList")
    public ResultEntity cellOrderInfoList (@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                           @RequestParam String userId,
                                           @RequestParam(value = "descOrAsc",defaultValue = "0") Integer descOrAsc,
                                           String searchInfo){
        QueryWrapper<CellOrderInfo> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(CellOrderInfo::getUserId,userId);
        if(StringUtils.isNotEmpty(searchInfo)){
            queryWrapper.lambda().and(
                    item -> item.or().eq(CellOrderInfo::getCollId,searchInfo)
                    .or()
                    .like(CellOrderInfo::getName,searchInfo)
            );
        }
        if(descOrAsc==0){
            queryWrapper.lambda().orderByDesc(CellOrderInfo::getCreateTime);
        }else {
            queryWrapper.lambda().orderByAsc(CellOrderInfo::getCreateTime);
        }
        Page<CellOrderInfo> page=new Page<>(pageNumber,pageSize);
        Page<CellOrderInfo> pageList=cellOrderInfoService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @ApiOperation("订单信息列表-根据id查询订单")
    @GetMapping("/cellOrderInfoById")
    public ResultEntity cellOrderInfoList (@RequestParam String id){
        return ResultEntity.success(cellOrderInfoService.getById(id),"查询成功");
    }
    @PostMapping("/addCellOrderInfo")
    @ApiOperation("添加藏品订单")
    public ResultEntity addcellOrderInfo(@RequestBody CellOrderInfo cellOrderInfo){
        cellOrderInfo.setCreateTime(new Date());
        cellOrderInfoService.save(cellOrderInfo);
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/editCellOrderInfo")
    @ApiOperation("修改藏品订单")
    public ResultEntity editcellOrderInfo(@RequestBody CellOrderInfo cellOrderInfo){
        cellOrderInfo.setUpdateTime(new Date());
        cellOrderInfoService.saveOrUpdate(cellOrderInfo);
        return ResultEntity.success("修改成功");
    }

    @Autowired
    private CellecHaveInfoService cellecHaveInfoService;
    @GetMapping("/cellecHaveInfoList")
    @ApiOperation("藏品用户拥有列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             @RequestParam(value = "userId") String userId,
                             String searchInfo){
        QueryWrapper<CellecHaveInfo> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotEmpty(searchInfo)){
            //多条件查询
            queryWrapper.lambda().and(
                    item -> item.or().eq(CellecHaveInfo::getCollecId,searchInfo)
                            .or().like(CellecHaveInfo::getName,searchInfo)
            );
        }
        queryWrapper.lambda().eq(CellecHaveInfo::getUserId,userId)
                .orderByDesc(CellecHaveInfo::getCreateTime);
        Page<CellecHaveInfo> page = new Page<>(pageNumber,pageSize);
        Page<CellecHaveInfo> pageList = cellecHaveInfoService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @PostMapping("/addCellecHaveInfo")
    @ApiOperation("添加藏品用户拥有")
    public ResultEntity addCellecHaveInfo(@RequestBody CellecHaveInfo cellecHaveInfo){
        cellecHaveInfo.setCreateTime(new Date());
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/editCellecHaveInfo")
    @ApiOperation("修改藏品用户拥有")
    public ResultEntity editCellecHaveInfo(@RequestBody CellecHaveInfo cellecHaveInfo){
        cellecHaveInfo.setUpdateTime(new Date());
        cellecHaveInfoService.saveOrUpdate(cellecHaveInfo);
        return ResultEntity.success("修改成功");
    }

    @Autowired
    private CellecPullInfoService cellecPullInfoService;
    @GetMapping("/cellecPullInfoList")
    @ApiOperation("藏品拉新用户列表")
    public ResultEntity cellecPullInfoList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                           @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                           @RequestParam(value = "userId")String userId,
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
    @Autowired
    private CellecIntegralService cellecIntegralService;
    @GetMapping("/queryCellecIntegralByUserId")
    @ApiOperation("藏品用户积分详情")
    public ResultEntity list(@RequestParam(value = "userId") String userId){
        QueryWrapper<CellecIntegral> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(CellecIntegral::getUserId,userId)
                .last(" limit 1");
        CellecIntegral cellecIntegral=cellecIntegralService.getOne(queryWrapper);
        return ResultEntity.success(cellecIntegral);
    }
    @Autowired
    private CellecIntegralActivityService cellecIntegralActivityService;
    @GetMapping("/cellecIntegralActivityList")
    @ApiOperation("藏品积分活动列表")
    public ResultEntity cellecIntegralActivityList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
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
    @GetMapping("/cellecIntegralActivityById")
    @ApiOperation("藏品积分活动详情")
    public ResultEntity cellecIntegralActivityById(@RequestParam(value = "id")Integer id){
        return ResultEntity.success(cellecIntegralActivityService.getById(id));
    }
    @Autowired
    private CellecIntegralRecordService cellecIntegralRecordService;
    @GetMapping("/cellecIntegralRecordList")
    @ApiOperation("藏品积分记录列表")
    public ResultEntity cellecIntegralRecordList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
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
    @GetMapping("/cellecIntegralRecordById")
    @ApiOperation("藏品积分记录详情")
    public ResultEntity cellecIntegralRecordById(@RequestParam(value = "id") String id){
        return ResultEntity.success(cellecIntegralRecordService.getById(id));
    }

    @Autowired
    private CollectionsMarketService collectionsMarketService;
    @ApiOperation("藏品用户寄售流转列表")
    @GetMapping("/queryCollectionsMarketList")
    public ResultEntity queryCollectionsMarketList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                   @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                   @RequestParam(value="userId") String userId){
        QueryWrapper<CollectionsMarket> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(CollectionsMarket::getUserId,userId)
                .or().eq(CollectionsMarket::getOtherUserId,userId);
        Page<CollectionsMarket> page=new Page<>(pageNumber,pageSize);
        Page<CollectionsMarket> pageList=collectionsMarketService.page(page,queryWrapper);
        return ResultEntity.success(pageList);
    }
    @ApiOperation("藏品用户寄售流转详情")
    @GetMapping("/queryCollectionsMarketById")
    public ResultEntity queryCollectionsMarketList(@RequestParam("id")String id){
        return ResultEntity.success(collectionsMarketService.getById(id));
    }
    @ApiOperation("二级缓存-订单信息")
    @GetMapping("queryOrderListInfo")
    public List<Map>queryOrderListInfo (@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                        @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                        @RequestParam(value = "userId") String userId){
        String sqlInfo=" select * from collec_order where user_id ='"+userId+"' limit "+pageNumber+","+pageSize;
        List<Map> resultSql=cellOrderInfoService.queryAllInfo(sqlInfo);
        return resultSql;
    }
    @ApiOperation("二级缓存-藏品信息")
    @GetMapping("/queryCollHaveInfoByUserId")
    public ResultEntity queryCollHaveInfoByUserId(@RequestParam("userId") String userId){
        CellecHaveInfo cellecHaveInfo=cellecHaveInfoService.queryCollHaveInfoByUserId(userId);
        System.out.println("-------------------------------------------------------------");
        CellecHaveInfo cellecHaveInfo1=cellecHaveInfoService.queryCollHaveInfoByUserId(userId);
        return ResultEntity.success(cellecHaveInfo);
    }
}

