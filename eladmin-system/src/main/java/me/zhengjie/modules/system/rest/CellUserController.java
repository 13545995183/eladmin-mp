package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.domain.CellUser;
import me.zhengjie.modules.system.service.CellUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
@Api(tags = "藏品用户信息接口")
@RequestMapping("/api/cellUser")
public class CellUserController {
    @Autowired
    private CellUserService cellUserService;
    @ApiOperation("查询所有藏品用户接口")
    @GetMapping("/list")
    public ResponseEntity<Page<CellUser>> cellInfoList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                       @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber){
        Page<CellUser> page=new Page<>(pageNumber,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        Page<CellUser> pageList=cellUserService.page(page,queryWrapper);
        ResponseEntity.status(200);
        return ResponseEntity.ok(pageList);
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品用户接口")
    public ResponseEntity add(@RequestBody CellUser cellUser){
        cellUser.setCreateTime(new Date());
        cellUserService.save(cellUser);
        return ResponseEntity.ok("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品用户接口")
    public ResponseEntity edit(@RequestBody CellUser cellUser){
        cellUser.setCreateTime(new Date());
        cellUserService.updateById(cellUser);
        return ResponseEntity.ok("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除用户")
    public ResponseEntity delete(@RequestParam String ids){
        cellUserService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResponseEntity.ok("删除成功");
    }
}
