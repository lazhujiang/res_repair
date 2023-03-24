package com.Cang.res.service.ucenter.service.impl;

import com.Cang.res.common.base.result.ResultCodeEnum;
import com.Cang.res.common.base.util.FormUtils;
import com.Cang.res.common.base.util.JwtInfo;
import com.Cang.res.common.base.util.JwtUtils;
import com.Cang.res.common.base.util.MD5;
import com.Cang.res.service.base.exception.RepairException;
import com.Cang.res.service.ucenter.entity.Member;
import com.Cang.res.service.ucenter.entity.vo.LoginVo;
import com.Cang.res.service.ucenter.entity.vo.RegisterVo;
import com.Cang.res.service.ucenter.mapper.MemberMapper;
import com.Cang.res.service.ucenter.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    private static final String INVEIT_CODE_KEY = "inveit_code";

    @Override
    public Integer countRegisterNum(String day) {
        return baseMapper.selectRegisterNumByDay(day);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void register(RegisterVo registerVo) {
        String nickname = registerVo.getNickname();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String code = registerVo.getCode();

        // 校验参数
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(code)
                || StringUtils.isEmpty(nickname)) {
            throw new RepairException(ResultCodeEnum.CODE_ERROR);
        }

        // 校验邀请码
        Jedis jedis = new Jedis("localhost");
        // 获取inveit_code列表中所有元素
        List<String> inveitCodes = jedis.lrange(INVEIT_CODE_KEY, 0, -1);

        // 查找元素并输出
        boolean found = false;
        int CodeNumber = 0;
        for (String inveitCode : inveitCodes) {
            if (code.equals(inveitCode)) {
                log.warn("找到该邀请码：" + inveitCode);
                found = true;
                log.warn("数值为：" + CodeNumber);
                jedis.lrem(INVEIT_CODE_KEY, CodeNumber, code);
                break;
            }
            CodeNumber++;
        }

        // 如果未找到抛出输出相应信息
        if (!found) {
            throw new RepairException(ResultCodeEnum.CODE_ERROR);
        }

        // 关闭Redis连接
        jedis.close();

        // 是否被注册
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = baseMapper.selectCount(queryWrapper);
        if (count > 0) {
            throw new RepairException(ResultCodeEnum.REGISTER_MOBLE_ERROR);
        }

        // 注册
        Member member = new Member();
        member.setNickname(nickname);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        member.setDisabled(false);
        member.setAvatar("");
        baseMapper.insert(member);
    }

    @Override
    public String login(LoginVo loginVo) {
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();

        // 校验参数
        if (StringUtils.isEmpty(mobile)
                || !FormUtils.isMobile(mobile)
                || StringUtils.isEmpty(password)) {
            throw new RepairException(ResultCodeEnum.PARAM_ERROR);
        }

        // 校验手机号
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Member member = baseMapper.selectOne(queryWrapper);
        if (member == null) {
            throw new RepairException(ResultCodeEnum.LOGIN_MOBILE_ERROR);
        }

        // 校验密码
        if (!MD5.encrypt(password).equals(member.getPassword())) {
            throw new RepairException(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }

        // 校验用户是否被禁用
        if (member.getDisabled()) {
            throw new RepairException(ResultCodeEnum.LOGIN_DISABLED_ERROR);
        }

        //登录：生成token
        JwtInfo jwtInfo = new JwtInfo();
        jwtInfo.setId(member.getId());
        jwtInfo.setNickname(member.getNickname());
        jwtInfo.setAvatar(member.getAvatar());
        String jwtToken = JwtUtils.getJwtToken(jwtInfo, 1800);

        return jwtToken;
    }
}
