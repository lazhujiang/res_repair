package com.Cang.res.service.repair.service;

import com.Cang.res.service.repair.entity.HpCategory;
import com.Cang.res.service.repair.entity.vo.CategoryVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
public interface HpCategoryService extends IService<HpCategory> {

    List<CategoryVo> nestedList();
}
