package com.Cang.res.service.repair.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ChapterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String title;
    private String resourceFile;
    private Date gmtModified;
    private Integer sort;
}