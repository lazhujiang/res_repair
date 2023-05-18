package com.Cang.res.service.repair.service;

import com.Cang.res.service.repair.entity.HpResource;
import com.Cang.res.service.repair.entity.form.ResourceInfoForm;
import com.Cang.res.service.repair.entity.vo.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
public interface HpResourceService extends IService<HpResource> {

    String saveResourceInfo(ResourceInfoForm resourceInfoForm);

    ResourceInfoForm getResourceInfoById(String id);

    void updateResourceInfoById(ResourceInfoForm resourceInfoForm);

    boolean removeResourceById(String id);

    IPage<ResourceVo> selectPage(Long page, Long limit, ResourceQueryVo resourceQueryVo);

    ResourcePublishVo getResourcePublishVoById(String id);

    boolean publishResourceById(String id);

    List<ResourceVo> selectNewResource();

    /**
     * 获取课程信息
     * @param id
     * @return
     */
    WebResourceVo selectWebResourceVoById(String id);

    List<HpResource> webSelectList(WebResourceQueryVo webResourceQueryVo);
}
