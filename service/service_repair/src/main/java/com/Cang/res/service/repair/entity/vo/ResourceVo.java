package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Date:2023/3/11 17:35
 * @Author:Cang
 */
@Data
public class ResourceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String categoryId;
    private String categoryParentId;
    private String title;
    private String cover;
    private Long version;
    private String status;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;

}
