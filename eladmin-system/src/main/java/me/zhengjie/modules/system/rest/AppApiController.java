package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.domain.CellUser;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.domain.User;
import me.zhengjie.modules.system.service.CellInfoService;
import me.zhengjie.modules.system.service.CellUserService;
import me.zhengjie.modules.system.service.DeptService;
import me.zhengjie.modules.system.service.UserService;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @ApiOperation("查询所有部门")
    @GetMapping("/getDeptList")
    public ResponseEntity<Page<Dept>> getDeptList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page page=new Page(pageNumber,pageSize);
        QueryWrapper<Dept> queryWrapper=new QueryWrapper<>();
        Page pageList=deptService.page(page,queryWrapper);
        return ResponseEntity.ok(pageList);
    }
    @ApiOperation("查询所有角色")
    @GetMapping("/getUserList")
    public ResponseEntity<Page<User>> getUserList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                  @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page page=new Page(pageNumber,pageSize);
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        Page pageList=userService.page(page,queryWrapper);
        return ResponseEntity.ok(pageList);
    }
    @ApiOperation("查询所有藏品用户角色")
    @GetMapping("/getCellUserList")
    public ResponseEntity<Page<CellUser>> getCellUserList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                          @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                          String CellUserPhone){
        Page page=new Page(pageNumber,pageSize);
        QueryWrapper<CellUser> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotEmpty(CellUserPhone)){
            queryWrapper.lambda().eq(CellUser::getPhone,CellUserPhone);
        }
        Page pageList=cellUserService.page(page,queryWrapper);
        return ResponseEntity.ok(pageList);
    }

    @ApiOperation("查询所有藏品信息接口")
    @GetMapping("/cellInfoList")
    public ResponseEntity<Page<CellInfo>> cellInfoList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                      @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page<CellInfo> page=new Page<>(pageNumber,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        Page<CellInfo> pageList=cellInfoService.page(page,queryWrapper);
        ResponseEntity.status(200);
        return ResponseEntity.ok(pageList);
    }
}

