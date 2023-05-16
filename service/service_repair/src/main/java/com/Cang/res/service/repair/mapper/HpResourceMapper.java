package com.Cang.res.service.repair.mapper;

import com.Cang.res.service.repair.entity.HpResource;
import com.Cang.res.service.repair.entity.vo.ResourcePublishVo;
import com.Cang.res.service.repair.entity.vo.ResourceVo;
import com.Cang.res.service.repair.entity.vo.WebResourceVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 资源 Mapper 接口
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Mapper
public interface HpResourceMapper extends BaseMapper<HpResource> {

    List<ResourceVo> selectPageByResourceQueryVo(
            // mp会自动组装分页参数
            Page<ResourceVo> pageParam,
            // mp会自动组装queryWrapper
            // @Param(Constants.WRAPPER) 和xml文件中的 ${ew.customSqlSegment} 对应
            @Param(Constants.WRAPPER) QueryWrapper<ResourceVo> queryWrapper);

    ResourcePublishVo selectResourcePublishVoById(String id);

    List<ResourceVo> selectHpResourceWithDescription();

    WebResourceVo selectWebResourceVoById(String id);
}
