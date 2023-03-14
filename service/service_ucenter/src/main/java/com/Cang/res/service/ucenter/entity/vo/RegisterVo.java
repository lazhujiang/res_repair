package com.Cang.res.service.ucenter.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Date:2023/3/14 12:25
 * @Author:Cang
 */
@Data
public class RegisterVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private String mobile;
    private String password;
    private String code;
}