package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date:2023/5/16 9:08
 * @Author:Cang
 */
@Data
public class WebResourceVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String title;
    private Integer lessonNum;
    private String cover;
    private Long viewCount;
    private String description;
    private String intro;
    private String avatar;
    private String categoryLevelOneId;
    private String categoryLevelOne;
    private String categoryLevelTwoId;
    private String categoryLevelTwo;
}
