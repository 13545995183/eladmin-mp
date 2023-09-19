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
* 2023-9-19
*
* */
@TableName("collec_bank_info")
@Api
@ApiModel("藏品用户账户信息")
@Getter
@Setter
public class CellecBankInfo {
    @TableId(type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "用户id")
    private BigInteger userId;
    @ApiModelProperty(value = "卡号")
    private String isCard;
    @ApiModelProperty(value = "银行卡id")
    private Integer wallId;
    @ApiModelProperty(value = "金额")
    private java.math.BigDecimal isMoney;
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
    @ApiModelProperty(value = "银行卡名称")
    private String wallName;
    @ApiModelProperty(value = "是否启用：0不启用/1启用")
    private Integer state;
    @ApiModelProperty(value = "是否删除0不/1是")
    private Integer isDel;
    @ApiModelProperty(value = "是否为主体公司")
    private Integer isCompany;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "默认文档")
    private String bgcolor;
    @ApiModelProperty(value = "金额")
    private java.math.BigDecimal falseMoney;
    @ApiModelProperty(value = "是否默认")
    private Integer isDefault;
    @ApiModelProperty(value = "链账户")
    private String nftAccount;
}
