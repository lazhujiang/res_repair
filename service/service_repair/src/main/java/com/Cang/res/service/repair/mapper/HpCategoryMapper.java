package com.Cang.res.service.repair.mapper;

import com.Cang.res.service.repair.entity.HpCategory;
import com.Cang.res.service.repair.entity.vo.CategoryVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Mapper
public interface HpCategoryMapper extends BaseMapper<HpCategory> {

    List<CategoryVo> selectNestedListByParentId(String parentId);
}
