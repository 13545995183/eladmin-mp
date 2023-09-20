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
 *
 * */
@TableName("collections_info")
@Api
@ApiModel("藏品拉新人员信息")
@Getter
@Setter
public class CellecPullInfo {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "拉新用户id")
    private BigInteger pullUserId;
    @ApiModelProperty(value = "受邀的用户id")
    private BigInteger coverPullUserId;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private String createName;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty(value = "更新人")
    private String updateName;
    @ApiModelProperty(value = "拉新用户id")
    private String pullUserName;
    @ApiModelProperty(value = "受邀的用户id")
    private String coverPullUserName;
}
