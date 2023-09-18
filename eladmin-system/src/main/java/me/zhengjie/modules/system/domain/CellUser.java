package me.zhengjie.modules.system.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
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
public class CellUser {
    @TableId(type = IdType.AUTO)
    //@ApiModelProperty(value = "用户id")
    private BigInteger id;
    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "性别")
    private String sex;
    @ApiModelProperty(value = "身份证号")
    private String idNumber;
    @ApiModelProperty(value = "头像")
    private String avatar;
    @ApiModelProperty(value = "状态 0:禁用，1:正常")
    private Integer status;
    @ApiModelProperty(value = "是否实名认证0:未认证,1:已认证")
    private Integer certification;
    @ApiModelProperty(value = "邀请码")
    private String invite;
    @ApiModelProperty(value = "受邀请码")
    private String isInvite;
    @ApiModelProperty(value = "是否开户")
    private Integer isOpen;
    @ApiModelProperty(value = "nft藏品id唯一")
    private String nftId;
    @ApiModelProperty(value = "昵称")
    private String loginName;
    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
