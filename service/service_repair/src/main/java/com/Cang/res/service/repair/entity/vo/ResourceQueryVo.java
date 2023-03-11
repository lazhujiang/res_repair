package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date:2023/3/11 17:36
 * @Author:Cang
 */
@Data
public class ResourceQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;
    private String categoryParentId;
    private String categoryId;
}
