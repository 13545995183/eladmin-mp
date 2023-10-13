package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CollectionsMarket;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CollectionsMarketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;

@RestController
@RequiredArgsConstructor
@Api(tags = "藏品用户寄售流转记录")
@RequestMapping("/api/cellAddressInfo")
public class CollectionsMarketController {
    @Autowired
    private CollectionsMarketService collectionsMarketService;
    @ApiOperation("藏品用户寄售流转列表")
    @GetMapping("/list")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             HttpRequest request){
        QueryWrapper<CollectionsMarket> queryWrapper=new QueryWrapper<>();
        Page<CollectionsMarket> page=new Page<>(pageNumber,pageSize);
        Page<CollectionsMarket> pageList=collectionsMarketService.page(page,queryWrapper);
        return ResultEntity.success(pageList);
    }
    @ApiOperation("添加用户寄售流转")
    @PostMapping("/add")
    public ResultEntity add(@RequestBody CollectionsMarket collectionsMarket){
        collectionsMarket.setCreateTime(new Date());
        collectionsMarketService.save(collectionsMarket);
        return ResultEntity.success("添加成功");
    }
    @ApiOperation("修改用户寄售流转")
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody CollectionsMarket collectionsMarket){
        collectionsMarket.setCreateTime(new Date());
        collectionsMarketService.save(collectionsMarket);
        return ResultEntity.success("修改成功");
    }
    @ApiOperation("删除用户寄售流转")
    @GetMapping("/delete")
    public ResultEntity delete(@RequestParam String ids){
        collectionsMarketService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
