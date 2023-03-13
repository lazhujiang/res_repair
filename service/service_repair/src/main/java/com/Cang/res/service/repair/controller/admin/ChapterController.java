package com.Cang.res.service.repair.controller.admin;

import com.Cang.res.common.base.result.R;
import com.Cang.res.service.repair.entity.HpChapter;
import com.Cang.res.service.repair.service.HpChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 资源 前端控制器
 * </p>
 *
 * @author Cang
 * @since 2023-03-11
 */
@CrossOrigin
@RestController
@RequestMapping("/admin/res/chapter")
@Api(tags = "文章管理")
@Slf4j
public class ChapterController {

    @Autowired
    private HpChapterService chapterService;

    @ApiOperation("新增资源")
    @PostMapping("save")
    public R save(
            @ApiParam(value = "资源对象", required = true)
            @RequestBody HpChapter chapter) {
        boolean result = chapterService.save(chapter);
        if (result) {
            return R.ok().message("保存成功");
        } else {
            return R.error().message("保存失败");
        }
    }

    @ApiOperation("根据id查询资源")
    @GetMapping("get/{id}")
    public R getResourceById(
            @ApiParam(value="资源id", required = true)
            @PathVariable String id){
        HpChapter chapter = chapterService.getResourceById(id);
        if (chapter != null) {
            return R.ok().data("item", chapter);
        } else {
            return R.error().message("请添加该资源的下载途径");
        }
    }

    @ApiOperation("根据id修改资源")
    @PutMapping("update")
    public R updateById(
            @ApiParam(value="章节对象", required = true)
            @RequestBody HpChapter chapter){
        boolean result = chapterService.updateById(chapter);
        if (result) {
            return R.ok().message("修改成功");
        } else {
            return R.error().message("数据不存在");
        }
    }

    @ApiOperation("根据ID删除资源")
    @DeleteMapping("remove/{id}")
    public R removeById(
            @ApiParam(value = "资源ID", required = true)
            @PathVariable String id){
        //TODO: 删除课程视频
        //此处调用vod中的删除视频文件的接口
        //videoService.removeMediaVideoByChapterId(id);

        //删除章节
        boolean result = chapterService.removeChapterById(id);
        if(result){
            return R.ok().message("删除成功");
        }else{
            return R.error().message("数据不存在");
        }
    }


}
