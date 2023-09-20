package me.zhengjie.modules.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.util.Date;

@TableName("collec_order")
@Api
@ApiModel("藏品用户订单信息")
@Getter
@Setter
public class CellOrderInfo {
    @ApiModelProperty(value = "id")
    private String id;
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    @ApiModelProperty(value = "藏品id")
    private Integer collId;
    @ApiModelProperty(value = "订单分类:0正常订单/1寄售订单")
    private Integer isType;
    @ApiModelProperty(value = "订单状态:0交易中/1交易成功/2取消交易/3订单超时")
    private Integer orderStats;
    @ApiModelProperty(value = "购买数量")
    private Integer buyNum;
    @ApiModelProperty(value = "购买价格")
    private java.math.BigDecimal buyPri;
    @ApiModelProperty(value = "微信支付信息")
    private String wxOrderInfo;
    @ApiModelProperty(value = "支付方式")
    private Integer paymentMethod;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "藏品名称")
    private String name;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "更新时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "是否启用")
    private BigInteger enabled;
    @ApiModelProperty(value = "商户id")
    private BigInteger merchantId;
    @ApiModelProperty(value = "是否删除;0否，1是")
    private Integer isDel;
    @ApiModelProperty(value = "更新人")
    private String stayMessage;
    @ApiModelProperty(value = "支付状态:0未支付/1支付完成/2过期")
    private String payStats;
    @ApiModelProperty(value = "订单关闭时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date closeTime;
    @ApiModelProperty(value = "关闭分类")
    private Integer closeType;
    @ApiModelProperty(value = "地址id")
    private Integer addressId;
    @ApiModelProperty(value = "交易状态")
    private Integer transactionType;
    @ApiModelProperty(value = "藏品名称")
    private String collName;
    @ApiModelProperty(value = "状态 1寄售/2铸造/3买入/4发行")
    private Integer buyType;
    @ApiModelProperty(value = "状态 1寄售/2铸造/3买入/4发行")
    private Integer sellType;
    @ApiModelProperty(value = "照片")
    private String image;
    @ApiModelProperty(value = "照片")
    private String smallImage;
    @ApiModelProperty(value = "账户id")
    private Integer bankId;
    @ApiModelProperty(value = "账户id")
    private Integer walletId;
    @ApiModelProperty(value = "卖账户id")
    private Integer sellBankId;
}

