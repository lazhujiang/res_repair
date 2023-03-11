package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date:2023/3/11 17:35
 * @Author:Cang
 */
@Data
public class ResourceVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String categoryParentTitle;
    private String categoryTitle;
    private String cover;
    private String gmtCreate;
}
