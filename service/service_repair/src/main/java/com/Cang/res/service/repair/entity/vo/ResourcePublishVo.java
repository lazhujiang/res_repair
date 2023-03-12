package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

/**
 * @Date:2023/3/12 19:29
 * @Author:Cang
 */
@Data
public class ResourcePublishVo {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private String cover;
    private String categoryParentTitle;
    private String categoryTitle;
}