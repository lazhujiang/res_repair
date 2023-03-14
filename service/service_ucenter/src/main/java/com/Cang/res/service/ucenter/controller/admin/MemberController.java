package com.Cang.res.service.ucenter.controller.admin;


import com.Cang.res.common.base.result.R;
import com.Cang.res.service.ucenter.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/admin/ucenter/member")
@Slf4j
public class MemberController {

    @Autowired
    private MemberService memberService;

    @ApiOperation("根据日期统计注册人数")
    @GetMapping("count-register-num/{day}")
    public R countRegisterNum(
            @ApiParam(value = "统计日期",required = true)
            @PathVariable String day) {
        Integer num = memberService.countRegisterNum(day);
        return R.ok().data("registerNum",num);
    }
}

