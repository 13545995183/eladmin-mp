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

@TableName("collec_bank_withdrawal_record")
@Api
@ApiModel("用户账户交易/提现/充值 记录")
@Getter
@Setter
public class CollecBankWithdrawalRecord {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "银行名称")
    private String bankName;
    @ApiModelProperty(value = "银行卡号")
    private String bankCode;
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    @ApiModelProperty(value = "减少/增加 状态")
    private Integer addReduce;
    @ApiModelProperty(value = "钱")
    private java.math.BigDecimal isMoney;
    @ApiModelProperty(value = "修改人")
    private String updateBy;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "账户id")
    private Integer bankId;
    @ApiModelProperty(value = "分类：0充值/1提现/2支出/3收入")
    private Integer isType;
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "账户id")
    private Integer isDel;
    @ApiModelProperty(value = "购买状态")
    private Integer buyAddReduce;
    @ApiModelProperty(value = "购买账户id")
    private Integer buyBankId;
}
