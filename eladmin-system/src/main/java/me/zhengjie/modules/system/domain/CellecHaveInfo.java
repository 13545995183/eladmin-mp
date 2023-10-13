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

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * qiansheng
 * 2023-9-20
 *
 * */
@TableName("collections_have")
@Api
@ApiModel("藏品用户拥有信息")
@Getter
@Setter
public class CellecHaveInfo implements Serializable {
    @ApiModelProperty(value = "id")
    @TableId(type = IdType.AUTO)
    private String id;
    @ApiModelProperty(value = "元数据")
    private String metadata;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "状态")
    private Integer stats;
    @ApiModelProperty(value = "分类")
    private Integer isType;
    @ApiModelProperty(value = "状态")
    private String imageinfo;
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    @ApiModelProperty(value = "创建藏品人名称")
    private String createPersonName;
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty(value = "商户id")
    private Integer merchantId;
    @ApiModelProperty(value = "照片")
    private String images;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "产品编号")
    private String qrcode;
    @ApiModelProperty(value = "寄卖价值")
    private java.math.BigDecimal sellPrice;
    @ApiModelProperty(value = "当前售价")
    private String currentPrice;
    @ApiModelProperty(value = "哈希唯一值")
    private String onlycode;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty(value = "限制人数")
    private Integer personNum;
    @ApiModelProperty(value = "流通量")
    private Integer circulation;
    @ApiModelProperty(value = "发行量")
    private Integer issue;
    @ApiModelProperty(value = "库存")
    private Integer stock;
    @ApiModelProperty(value = "用户分类")
    private String userType;
    @ApiModelProperty(value = "销量")
    private Integer sales;
    @ApiModelProperty(value = "分类：寄/买/发行/铸造")
    private Integer buySellType;
    @ApiModelProperty(value = "拥有用户id")
    private BigInteger haveId;
    @ApiModelProperty(value = "元数据url")
    private Integer metadata_url;
    @ApiModelProperty(value = "藏品id")
    private Integer collecId;
    @ApiModelProperty(value = "照片")
    private String smallImage;
    @ApiModelProperty(value = "唯一值")
    private BigInteger  onlyInfo;
    @ApiModelProperty(value = "是否转赠")
    private Integer  isSubgift;
    @ApiModelProperty(value = "是否预售")
    private Integer  isPresale;
}
