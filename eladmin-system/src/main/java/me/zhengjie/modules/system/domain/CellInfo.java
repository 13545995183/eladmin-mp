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

@TableName("collections_info")
@Api
@ApiModel("藏品用户信息")
@Getter
@Setter
public class CellInfo {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "标题")
    private String title;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "分类")
    private Integer isType;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "创建藏品名称")
    private String createPersonName;
    @ApiModelProperty(value = "商户id")
    private BigInteger merchantId;
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    @ApiModelProperty(value = "照片")
    private String images;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "产品编号")
    private String qrcode;
    @ApiModelProperty(value = "哈希唯一值")
    private String onlycode;
    @ApiModelProperty(value = "开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    @ApiModelProperty(value = "结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    @ApiModelProperty(value = "发行量")
    private Integer issue;
    @ApiModelProperty(value = "流通量")
    private Integer circulation;
    @ApiModelProperty(value = "库存")
    private Integer stock;
    @ApiModelProperty(value = "销量")
    private Integer sales;
    @ApiModelProperty(value = "买卖分类：寄/买/发行/铸造")
    private Integer buySellType;
    @ApiModelProperty(value = "附件")
    private String imageinfo;
}
