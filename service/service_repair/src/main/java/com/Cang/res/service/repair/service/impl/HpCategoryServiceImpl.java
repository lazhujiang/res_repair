package com.Cang.res.service.repair.service.impl;

import com.Cang.res.service.repair.entity.HpCategory;
import com.Cang.res.service.repair.entity.vo.CategoryVo;
import com.Cang.res.service.repair.mapper.HpCategoryMapper;
import com.Cang.res.service.repair.service.HpCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Service
public class HpCategoryServiceImpl extends ServiceImpl<HpCategoryMapper, HpCategory> implements HpCategoryService {

    @Override
    public List<CategoryVo> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }

}
