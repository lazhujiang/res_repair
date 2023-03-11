package com.Cang.res.service.repair.controller.admin;


import com.Cang.res.service.repair.entity.HpCategory;
import com.Cang.res.service.repair.entity.vo.CategoryVo;
import com.Cang.res.service.repair.service.HpCategoryService;
import com.Cang.res.common.base.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Cang
 * @since 2023-03-10
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/res/category")
@Api(tags = "资源类别管理")
@Slf4j
public class CategoryController {

    @Autowired
    private HpCategoryService categoryService;

    @ApiOperation("查看所有类别")
    @GetMapping("list")
    public R findAll() {
        List<HpCategory> list = categoryService.list();
        return R.ok().data("items",list);
    }


    // 在首页导航栏展示的功能
    @ApiOperation("多级别类别管理")
    @GetMapping("nested-list")
    public R nestedList() {
        List<CategoryVo> categoryVoList = categoryService.nestedList();
        return R.ok().data("items",categoryVoList);
    }

}

