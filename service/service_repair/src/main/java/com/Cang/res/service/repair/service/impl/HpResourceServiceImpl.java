package com.Cang.res.service.repair.service.impl;

import com.Cang.res.service.repair.entity.HpResource;
import com.Cang.res.service.repair.entity.HpResourceDescription;
import com.Cang.res.service.repair.entity.form.ResourceInfoForm;
import com.Cang.res.service.repair.entity.vo.ResourcePublishVo;
import com.Cang.res.service.repair.entity.vo.ResourceQueryVo;
import com.Cang.res.service.repair.entity.vo.ResourceVo;
import com.Cang.res.service.repair.mapper.HpResourceDescriptionMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.Cang.res.service.repair.mapper.HpResourceMapper;
import com.Cang.res.service.repair.service.HpResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@Service
public class HpResourceServiceImpl extends ServiceImpl<HpResourceMapper, HpResource> implements HpResourceService {

    @Autowired
    private HpResourceDescriptionMapper resourceDescriptionMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String saveResourceInfo(ResourceInfoForm resourceInfoForm) {

        // 保存资源基本信息
        HpResource resource = new HpResource();
        //Resource.setStatus(HpResource.Resource_DRAFT);
        // 如果属性一样，则将ResourceInfoForm的内容拷贝到Resource中
        // BeanUtils.copyProperties会进行类型转换；
        // BeanUtils.copyProperties方法简单来说就是将两个字段相同的对象进行属性值的复制。
        // 如果 两个对象之间存在名称不相同的属性，则 BeanUtils 不对这些属性进行处理，需要程序手动处理。
        BeanUtils.copyProperties(resourceInfoForm,resource);
        baseMapper.insert(resource);

        //保存ResourceDescription
        HpResourceDescription resourceDescription = new HpResourceDescription();
        resourceDescription.setDescription(resourceInfoForm.getDescription());
        resourceDescription.setId(resource.getId());
        resourceDescriptionMapper.insert(resourceDescription);

        return resource.getId();
    }

    @Override
    public ResourceInfoForm getResourceInfoById(String id) {
        // 从Resource表中取数据
        HpResource Resource = baseMapper.selectById(id);
        if (Resource == null) {
            return null;
        }

        // 从Resource_description表中取数据
        HpResourceDescription ResourceDescription = resourceDescriptionMapper.selectById(id);

        // 创建ResourceInfoForm对象
        ResourceInfoForm ResourceInfoForm = new ResourceInfoForm();
        BeanUtils.copyProperties(Resource,ResourceInfoForm);
        ResourceInfoForm.setDescription(ResourceDescription.getDescription());

        return ResourceInfoForm;
    }

    @Override
    public void updateResourceInfoById(ResourceInfoForm resourceInfoForm) {
        // 保存课程基本信息
        HpResource resource = new HpResource();
        // 如果属性一样，则将ResourceInfoForm的内容拷贝到Resource中
        // BeanUtils.copyProperties会进行类型转换；
        // BeanUtils.copyProperties方法简单来说就是将两个字段相同的对象进行属性值的复制。
        // 如果 两个对象之间存在名称不相同的属性，则 BeanUtils 不对这些属性进行处理，需要程序手动处理。
        BeanUtils.copyProperties(resourceInfoForm,resource);
        baseMapper.updateById(resource);

        //保存ResourceDescription
        HpResourceDescription resourceDescription = new HpResourceDescription();
        resourceDescription.setDescription(resourceInfoForm.getDescription());
        resourceDescription.setId(resource.getId());
        resourceDescriptionMapper.updateById(resourceDescription);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeResourceById(String id) {
        // 章节信息：chapter
        //QueryWrapper<Chapter> chapterQueryWrapper = new QueryWrapper<>();
        //courseCollectQueryWrapper.eq("course_id",id);
        //chapterMapper.delete(chapterQueryWrapper);

        // 课程详情信息：resource_description
        resourceDescriptionMapper.deleteById(id);

        // 课程信息：resource
        return this.removeById(id);
    }

    @Override
    public IPage<ResourceVo> selectPage(Long page, Long limit, ResourceQueryVo resourceQueryVo) {
        QueryWrapper<ResourceVo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("c.gmt_create");

        String title = resourceQueryVo.getTitle();
        String categoryParentId = resourceQueryVo.getCategoryParentId();
        String categoryId = resourceQueryVo.getCategoryId();

        if (!StringUtils.isEmpty(title)) {
            queryWrapper.like("c.title",title);
        }

        if (!StringUtils.isEmpty(categoryParentId)) {
            queryWrapper.eq("c.category_parent_id",categoryParentId);
        }

        if (!StringUtils.isEmpty(categoryId)) {
            queryWrapper.eq("c.category_id",categoryId);
        }

        Page<ResourceVo> pageParam = new Page<>(page, limit);
        // 放入分页参数和查询条件参数，mp会自动组装
        List<ResourceVo> records =  baseMapper.selectPageByResourceQueryVo(pageParam,queryWrapper);
        return pageParam.setRecords(records);
    }

    /*@Cacheable(value = "index", key = "'selectHotResource'")
    @Override
    public List<HpResource> selectHotResource() {

        QueryWrapper<HpResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("view_count");
        queryWrapper.last("limit 8");

        return baseMapper.selectList(queryWrapper);
    }*/

    @Override
    public ResourcePublishVo getResourcePublishVoById(String id) {
        return baseMapper.selectResourcePublishVoById(id);
    }

    @Override
    public boolean publishResourceById(String id) {
        HpResource resource = new HpResource();
        resource.setId(id);
        return this.updateById(resource);
    }
}
