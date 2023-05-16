package com.Cang.res.service.repair.controller.api;


import com.Cang.res.common.base.result.R;
import com.Cang.res.service.repair.entity.vo.ChapterVo;
import com.Cang.res.service.repair.entity.vo.WebResourceVo;
import com.Cang.res.service.repair.service.HpChapterService;
import com.Cang.res.service.repair.service.HpResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
//@CrossOrigin
@Api(tags = "资源")
@RestController
@RequestMapping("/api/res/resource")
public class CustomApiResourceController {

    @Autowired
    private HpResourceService hpResourceService;
    @Autowired
    private HpChapterService hpChapterService;

    /*@ApiOperation("资源列表")
    @GetMapping("list")
    public R list(
            @ApiParam(value = "查询对象", required = true)
                    WebResourceQueryVo webResourceQueryVo) {
        List<HpResource> resourceList = hpResourceService.webSelectList(webResourceQueryVo);
        return R.ok().data("resourceList", resourceList);
    }*/

    @ApiOperation("根据ID查询资源")
    @GetMapping("get/{resourceId}")
    public R getById(
            @ApiParam(value = "资源ID", required = true)
            @PathVariable String resourceId) {
        // 查询资源信息
        WebResourceVo webResourceVo = hpResourceService.selectWebResourceVoById(resourceId);
        // 查询当前资源的章节信息
        List<ChapterVo> chapterVoList = hpChapterService.nestedList(resourceId);

        return R.ok().data("resource", webResourceVo).data("chapterVoList", chapterVoList);
    }

    /*@ApiOperation(value = "根据资源id查询资源信息")
    @GetMapping("inner/get-resource-dto/{resourceId}")
    public ResourceDto getResourceDtoById(
            @ApiParam(value = "资源ID", required = true)
            @PathVariable String resourceId) {
        ResourceDto resourceDto = resourceService.getResourceDtoById(resourceId);
        return resourceDto;
    }*/
}

