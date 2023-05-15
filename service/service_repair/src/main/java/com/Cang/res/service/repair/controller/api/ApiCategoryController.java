package com.Cang.res.service.repair.controller.api;

import com.Cang.res.common.base.result.R;
import com.Cang.res.service.repair.entity.vo.CategoryVo;
import com.Cang.res.service.repair.service.HpCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Date:2023/4/11 8:33
 * @Author:Cang
 */
@CrossOrigin
@Api(tags = "资源分类")
@RestController
@RequestMapping("/api/res/category")
public class ApiCategoryController {

    @Autowired
    private HpCategoryService categoryService;

    @ApiOperation("嵌套数据列表")
    @GetMapping("nested-list")
    public R nestedList() {
        List<CategoryVo> categoryVoList = categoryService.nestedList();
        return R.ok().data("items",categoryVoList);
    }
}