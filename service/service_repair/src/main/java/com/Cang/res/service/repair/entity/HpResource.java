package com.Cang.res.service.repair.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="HpResource对象", description="资源")
public class HpResource implements Serializable {

    private static final long serialVersionUID=1L;

    public static final String RESOURCE_DRAFT = "Draft";//未发布
    public static final String RESOURCE_NORMAL = "Normal";//已发布

    @ApiModelProperty(value = "资源ID")
      @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value = "资源类别ID")
    private String categoryId;

    @ApiModelProperty(value = "资源类别父级ID")
    private String categoryParentId;

    @ApiModelProperty(value = "资源标题")
    private String title;

    @ApiModelProperty(value = "资源封面图片路径")
    private String cover;

    @ApiModelProperty(value = "乐观锁")
    private Long version;

    @ApiModelProperty(value = "资源状态 Draft未发布  Normal已发布")
    private String status;

    @ApiModelProperty(value = "创建时间")
      @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "更新时间")
      @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
