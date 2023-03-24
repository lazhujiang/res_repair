package com.Cang.res.service.repair.service.impl;

import com.Cang.res.service.repair.entity.HpCategory;
import com.Cang.res.service.repair.entity.vo.CategoryVo;
import com.Cang.res.service.repair.mapper.HpCategoryMapper;
import com.Cang.res.service.repair.service.HpCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Slf4j
@Service
public class HpCategoryServiceImpl extends ServiceImpl<HpCategoryMapper, HpCategory> implements HpCategoryService {

    //注入操作redis的对象redisTemplate
    @Resource
    private RedisTemplate<String, List<CategoryVo>> redisTemplate;
    
    @Override
    public List<CategoryVo> nestedList() {
        //记录方法开始时间
        long start = System.currentTimeMillis();
        //服务层调用持久层代码，访问数据库
        //1、先从缓存里面查，如果缓存有就直接返回，没有在查数据库
        List<CategoryVo> list = redisTemplate.boundValueOps("category_list").get();
        if (list != null) {
            log.info("数据从redis中获取：{}", list);
            //记录方法结束时间
            long end = System.currentTimeMillis();
            //打印查询详情所用时间
            log.info("详情查询使用时间：{}", start - end);
            return list;
        } else {
            //缓存中没有从数据库中查
            List<CategoryVo> category = baseMapper.selectNestedListByParentId("0");
            //并且保存到redis当中
            redisTemplate.boundValueOps("category_list").set(category);
            log.info("数据从数据库中获取中获取：{}", category);
            //记录方法结束时间
            long end = System.currentTimeMillis();
            //打印查询详情所用时间
            log.info("详情查询使用时间：{}", start - end);
            return category;
        }
        //return baseMapper.selectNestedListByParentId("0");
    }

}
