package com.Cang.res.service.repair.controller.api;

import com.Cang.res.common.base.result.R;
import com.Cang.res.service.repair.entity.HpResource;
import com.Cang.res.service.repair.entity.vo.ResourceVo;
import com.Cang.res.service.repair.service.HpResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@CrossOrigin
@Api(tags = "首页")
@RestController
@RequestMapping("/api/res/index")
public class ApiIndexController {

    @Autowired
    private HpResourceService resourceService;

    // 查询最新资源
    @ApiOperation("最新资源的首页数据")
    @GetMapping
    public R index() {

        // 查询热门课程
        List<ResourceVo> resourceList =  resourceService.selectNewResource();
        for (ResourceVo resourceVo : resourceList) {
            System.out.println(resourceVo);
        }

        return R.ok().data("resourceList",resourceList);
    }


}