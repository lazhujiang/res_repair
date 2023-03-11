package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date:2023/3/10 19:16
 * @Author:Cang
 */

@Data
public class CategoryVo implements Serializable {

    private static final long serialVersionUID=1L;

    private String id;
    private String title;
    private List<CategoryVo> children = new ArrayList<>();

}
