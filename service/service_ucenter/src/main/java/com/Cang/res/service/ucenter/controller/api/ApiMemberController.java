package com.Cang.res.service.ucenter.controller.api;

import com.Cang.res.common.base.result.R;
import com.Cang.res.common.base.result.ResultCodeEnum;
import com.Cang.res.common.base.util.JwtInfo;
import com.Cang.res.common.base.util.JwtUtils;
import com.Cang.res.service.base.exception.RepairException;
import com.Cang.res.service.ucenter.entity.vo.LoginVo;
import com.Cang.res.service.ucenter.entity.vo.RegisterVo;
import com.Cang.res.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */

@Api(tags = "会员管理")
@CrossOrigin
@RestController
@RequestMapping("/api/ucenter/member")
@Slf4j
public class ApiMemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "会员注册")
    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo) {
        memberService.register(registerVo);
        return R.ok();
    }

    @ApiOperation(value = "会员登录")
    @PostMapping("login")
    public R login(@RequestBody LoginVo loginVo){
        String token = memberService.login(loginVo);
        return R.ok().data("token", token).message("登录成功");
    }

    @ApiOperation(value = "根据token获取登录信息")
    @GetMapping("get-login-info")
    public R getLoginInfo(HttpServletRequest request) {
        try {
            JwtInfo jwtInfo = JwtUtils.getMemberIdByJwtToken(request);
            return R.ok().data("userInfo",jwtInfo);
        } catch (Exception e) {
            log.error("解析用户信息失败：" + e.getMessage());
            throw new RepairException(ResultCodeEnum.FETCH_USERINFO_ERROR);
        }
    }

}