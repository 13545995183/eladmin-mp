package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellInfo;
import me.zhengjie.modules.system.domain.Dept;
import me.zhengjie.modules.system.service.CellInfoService;
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
@Api(tags = "藏品信息接口")
@RequestMapping("/api/cellInfo")
public class CellInfoController  {
    @Autowired
    private CellInfoService cellInfoService;
    @ApiOperation("查询所有藏品信息接口")
    @GetMapping("/list")
    public ResponseEntity<Page<CellInfo>> cellInfoList(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                                                      @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                                                      CellInfo cellInfo,
                                                      HttpRequest request){
        Page<CellInfo> page=new Page<>(pageNumber,pageSize);
        QueryWrapper queryWrapper=new QueryWrapper();
        Page<CellInfo> pageList=cellInfoService.page(page,queryWrapper);
        ResponseEntity.status(200);
        return ResponseEntity.ok(pageList);
    }
}
