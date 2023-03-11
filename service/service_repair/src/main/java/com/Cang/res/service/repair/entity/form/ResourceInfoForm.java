package com.Cang.res.service.repair.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Date:2023/3/11 9:49
 * @Author:Cang
 */
@ApiModel("资源基本信息")
@Data
public class ResourceInfoForm implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源ID")
    private String id;

    @ApiModelProperty(value = "资源专业ID")
    private String categoryId;

    @ApiModelProperty(value = "资源专业父级ID")
    private String categoryParentId;

    @ApiModelProperty(value = "资源标题")
    private String title;

    @ApiModelProperty(value = "课程封面图片路径")
    private String cover;

    @ApiModelProperty(value = "课程简介")
    private String description;
}