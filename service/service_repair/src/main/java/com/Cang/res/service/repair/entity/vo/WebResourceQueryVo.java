package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class WebResourceQueryVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String categoryParentId;
    private String categoryId;
    private String gmtCreateSort;

    //private Integer type; //价格正序：1，价格倒序：2
}