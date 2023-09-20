package me.zhengjie.modules.system.rest;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.system.domain.CellecHaveInfo;
import me.zhengjie.modules.system.domain.CellecIntegral;
import me.zhengjie.modules.system.domain.vo.ResultEntity;
import me.zhengjie.modules.system.service.CellecIntegralService;
import me.zhengjie.utils.StringUtils;
import net.dreamlu.mica.core.result.R;
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
@Api(tags = "藏品积分接口")
@RequestMapping("/api/cellIntegral")
public class CellecIntegralController {
    @Autowired
    private CellecIntegralService cellecIntegralService;
    @GetMapping("/list")
    @ApiOperation("藏品积分列表")
    public ResultEntity list(@RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,
                             @RequestParam(value = "pageNumber",defaultValue = "1") Integer pageNumber,
                             String userId,
                             String searchInfo,
                             @RequestParam(value = "descOrAsc",defaultValue = "desc")String descOrAsc){
        QueryWrapper<CellecIntegral> queryWrapper=new QueryWrapper<>();
        Page<CellecIntegral> page=new Page<>(pageNumber,pageSize);
        if(StringUtils.isNotEmpty(userId)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecIntegral::getUserId,userId));
        }
        if(StringUtils.isNotEmpty(searchInfo)){
            queryWrapper.lambda().and(item ->
                    item.like(CellecIntegral::getLoginName,searchInfo)
                    );
        }
        if(descOrAsc.equals("desc")){
            queryWrapper.lambda().orderByDesc(CellecIntegral::getFraction);
        }else if(descOrAsc.equals("asc")){
            queryWrapper.lambda().orderByAsc(CellecIntegral::getFraction);
        }else{
            queryWrapper.lambda().orderByAsc(CellecIntegral::getCreateTime);
        }
        Page<CellecIntegral> pageList=cellecIntegralService.page(page,queryWrapper);
        return ResultEntity.success(pageList.getRecords(),"查询成功");
    }
    @PostMapping("/add")
    @ApiOperation("添加藏品积分")
    public ResultEntity add(@RequestBody CellecIntegral cellecIntegral){
        cellecIntegral.setCreateTime(new Date());
        cellecIntegralService.save(cellecIntegral);
        return ResultEntity.success("添加成功");
    }
    @PostMapping("/edit")
    @ApiOperation("修改藏品积分")
    public ResultEntity edit(@RequestBody CellecIntegral cellecIntegral){
        cellecIntegral.setUpdateTime(new Date());
        cellecIntegralService.saveOrUpdate(cellecIntegral);
        return ResultEntity.success("修改成功");
    }
    @GetMapping("/delete")
    @ApiOperation("删除藏品积分")
    public ResultEntity delete (@RequestParam(value = "ids") String ids){
        cellecIntegralService.removeBatchByIds(Arrays.asList(ids.split(",")));
        return ResultEntity.success("删除成功");
    }
}
