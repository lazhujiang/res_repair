package com.Cang.res.service.cms.service.impl;

import com.Cang.res.service.cms.entity.Ad;
import com.Cang.res.service.cms.entity.vo.AdVo;
import com.Cang.res.service.cms.mapper.AdMapper;
import com.Cang.res.service.cms.service.AdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 广告推荐 服务实现类
 * </p>
 *
 * @author Cang
 * @since 2023-04-10
 */
@Service
public class AdServiceImpl extends ServiceImpl<AdMapper, Ad> implements AdService {

    //注入操作redis的对象redisTemplate
    @Resource
    private RedisTemplate<String, List<Ad>> redisTemplate;

    @Cacheable(value = "index",key = "'selectByAdTypeId'")
    @Override
    public List<Ad> selectByAdTypeId(String adTypeId) {
        List<Ad> list = redisTemplate.boundValueOps("ad_list").get();
        if (list != null) {
            return list;
        } else {
            QueryWrapper<Ad> queryWrapper = new QueryWrapper<>();
            queryWrapper.orderByAsc("sort", "id");
            queryWrapper.eq("type_id", adTypeId);
            List<Ad> ad = baseMapper.selectList(queryWrapper);
            //并且保存到redis当中
            redisTemplate.boundValueOps("ad_list").set(ad);
            return ad;
        }
    }
}
