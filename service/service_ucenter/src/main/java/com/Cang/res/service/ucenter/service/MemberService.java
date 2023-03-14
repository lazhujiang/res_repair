package com.Cang.res.service.ucenter.service;

import com.Cang.res.service.ucenter.entity.Member;
import com.Cang.res.service.ucenter.entity.vo.RegisterVo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
public interface MemberService extends IService<Member> {

    Integer countRegisterNum(String day);

    void register(RegisterVo registerVo);
}
