package com.Cang.res.service.oss.controller.admin;

import com.Cang.res.common.base.result.R;
import com.Cang.res.common.base.result.ResultCodeEnum;
import com.Cang.res.service.base.exception.RepairException;
import com.Cang.res.service.oss.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * @Date:2023/6/4 16:27
 * @Author:Cang
 */
@Api(tags = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/admin/oss/file")
@Slf4j
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     * @param file
     * @param module
     * @return
     * @throws IOException
     */
    @ApiOperation("文件上传")
    @PostMapping("upload")
    public R upload(
            @ApiParam(value = "文件", required = true)
            @RequestParam("file") MultipartFile file,

            @ApiParam(value = "模块", required = true)
            @RequestParam("module") String module) {
        try {
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            String uploadUrl = fileService.upload(inputStream, module, originalFilename);

            //返回r对象
            return R.ok().message("文件上传成功").data("url",uploadUrl);
        } catch (Exception e) {
            throw new RepairException(ResultCodeEnum.FILE_UPLOAD_ERROR);
        }
    }

    @ApiOperation("文件删除")
    @DeleteMapping("remove")
    public R removeFile(
            @ApiParam(value = "要删除的文件路径", required = true)
            @RequestBody String url) {
        fileService.removeFile(url);
        return R.ok().message("文件删除成功");
    }

    @ApiOperation("测试")
    @GetMapping("test")
    public R test() {
        log.info("oss test被调用");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
}
