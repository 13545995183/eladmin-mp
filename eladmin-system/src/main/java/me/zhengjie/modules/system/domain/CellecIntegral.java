package me.zhengjie.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;
/**
 * qiansheng
 * 2023-9-20
 * */
@TableName("collec_integral_total")
@Api
@ApiModel("藏品用户积分表")
@Getter
@Setter
public class CellecIntegral {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    @ApiModelProperty(value = "分数")
    private Integer fraction;
    @ApiModelProperty(value = "历史积分")
    private Integer totalFraction;
    @ApiModelProperty(value = "名称")
    private String loginName;
    @ApiModelProperty(value = "积分操作 0 增加/1 减少")
    private Integer addReduce;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
}
