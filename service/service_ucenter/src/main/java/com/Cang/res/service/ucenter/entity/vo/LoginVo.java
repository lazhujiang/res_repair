package com.Cang.res.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date:2023/3/15 17:30
 * @Author:Cang
 */
@Data
public class LoginVo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String mobile;
    private String password;
}