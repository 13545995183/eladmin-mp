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

@TableName("user_info")
@Api
@ApiModel("藏品用户信息")
@Getter
@Setter
public class CollectionsMarket {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "用户id")
    private BigInteger id;
    @ApiModelProperty(value = "藏品拥有表id")
    private Integer haveId;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "状态，-1-已取消，0-交易中，1-交易完成")
    private Integer stats;
    @ApiModelProperty(value = "分类，1-寄售，2-买入")
    private Integer type;
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
    @ApiModelProperty(value = "照片")
    private String images;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "产品编号")
    private String qrcode;
    @ApiModelProperty(value = "价格")
    private java.math.BigDecimal sellPrice;
    @ApiModelProperty(value = "哈希唯一值")
    private String onlycode;
    @ApiModelProperty(value = "元数据url")
    private String metadataUrl;
    @ApiModelProperty(value = "藏品id")
    private Integer collecId;
    @ApiModelProperty(value = "唯一值")
    private Integer onlyInfo;
    @ApiModelProperty(value = "用户id（寄售的卖家、买入的买家）")
    private Integer userId;
    @ApiModelProperty(value = "另一方用户id（寄售的买家、买入的卖家）")
    private Integer otherUserId;
    @ApiModelProperty(value = "结束时间，取消/成交时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date finishTime;
}
