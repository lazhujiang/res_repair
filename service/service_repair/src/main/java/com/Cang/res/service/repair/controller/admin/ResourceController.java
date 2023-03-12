package com.Cang.res.service.repair.controller.admin;

import com.Cang.res.common.base.result.R;
import com.Cang.res.service.repair.entity.form.ResourceInfoForm;
import com.Cang.res.service.repair.entity.vo.ResourcePublishVo;
import com.Cang.res.service.repair.entity.vo.ResourceQueryVo;
import com.Cang.res.service.repair.entity.vo.ResourceVo;
import com.Cang.res.service.repair.service.HpResourceService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Date:2023/3/11 9:46
 * @Author:Cang
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/res/resource")
@Api(tags = "资源管理")
@Slf4j
public class ResourceController {

    @Autowired
    private HpResourceService resourceService;


    @ApiOperation(value = "新增资源")
    @PostMapping("save-resource-info")
    public R saveCourseInfo(
            @ApiParam(value = "资源基本信息", required = true)
            @RequestBody ResourceInfoForm resourceInfoForm) {
        String resourceId = resourceService.saveResourceInfo(resourceInfoForm);
        return R.ok().data("resourceId", resourceId).message("保存成功");
    }

    @ApiOperation(value = "根据ID查询资源")
    @GetMapping("resource-info/{id}")
    public R getById(
            @ApiParam(value = "资源ID", required = true)
            @PathVariable String id) {
        ResourceInfoForm resourceInfoForm = resourceService.getResourceInfoById(id);
        if (resourceInfoForm != null) {
            return R.ok().data("item",resourceInfoForm);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation(value = "更新资源")
    @PutMapping("update-resource-info")
    public R updateResourceInfoById(
            @ApiParam(value = "资源基本信息", required = true)
            @RequestBody ResourceInfoForm resourceInfoForm) {

        resourceService.updateResourceInfoById(resourceInfoForm);
        return R.ok().message("修改成功");
    }

    @ApiOperation(value = "根据ID删除资源")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "资源ID",required = true)
            @PathVariable String id) {
        // TODO 删除视频：VOD
        // 在此处调用vod中的删除视频文件的接口
        //videoService.removeMediaVideoByCourseId(id);

        // 删除封面：OSS
        //courseService.removeCoverById(id);

        // 删除资源
        boolean result = resourceService.removeResourceById(id);
        if (result) {
            return R.ok().message("删除成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("资源分页列表")
    @GetMapping("list/{page}/{limit}")
    public R listPage(@ApiParam(value = "当前页码", required = true) @PathVariable Long page,
                      @ApiParam(value = "每页记录数", required = true) @PathVariable Long limit,
                      @ApiParam("资源列表查询对象") ResourceQueryVo resourceQueryVo){

        IPage<ResourceVo> pageModel = resourceService.selectPage(page, limit, resourceQueryVo);
        List<ResourceVo> records = pageModel.getRecords();
        long total = pageModel.getTotal();
        return R.ok().data("total", total).data("rows", records);
    }

    @ApiOperation("根据ID获取资源发布信息")
    @GetMapping("resource-publish/{id}")
    public R getResourcePublishVoById(
            @ApiParam(value = "资源ID",required = true)
            @PathVariable String id) {
        ResourcePublishVo resourcePublishVo = resourceService.getResourcePublishVoById(id);
        if (resourcePublishVo != null) {
            return R.ok().data("item",resourcePublishVo);
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据id发布资源")
    @PutMapping("publish-resource/{id}")
    public R publishResourceVoById(
            @ApiParam(value = "资源ID",required = true)
            @PathVariable String id) {
        boolean result = resourceService.publishResourceById(id);
        if (result) {
            return R.ok().message("发布成功");
        } else {
            return R.error().message("数据不存在");
        }
    }
}
