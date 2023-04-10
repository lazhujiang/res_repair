package com.Cang.res.service.repair.controller.api;

import com.Cang.res.common.base.result.R;
import com.Cang.res.service.repair.entity.HpResource;
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

    /*// 查询热门课程
    @ApiOperation("课程和讲师的首页数据")
    @GetMapping
    public R index() {

        // 查询热门课程
        List<HpResource> resourceList =  resourceService.selectHotResource();

        return R.ok().data("resourceList",resourceList);
    }*/


}