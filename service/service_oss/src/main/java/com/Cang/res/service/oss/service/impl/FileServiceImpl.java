package com.Cang.res.service.oss.service.impl;

import com.Cang.res.service.oss.service.FileService;
import com.Cang.res.service.oss.util.OssProperties;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Date:2023/6/4 16:30
 * @Author:Cang
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String upload(InputStream inputStream, String module, String originalFilename) {

        //读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);
        if (!ossClient.doesBucketExist(bucketname)) {
            //创建bucket
            ossClient.createBucket(bucketname);
            //设置oss实例的访问权限：公共读
            ossClient.setBucketAcl(bucketname, CannedAccessControlList.PublicRead);
        }

        //构建日期路径：avatar/2019/02/26/文件名
        //构建objectName：文件路径 avatar/2020/04/15/default.jpg
        String folder = new DateTime().toString("yyyy/MM/dd");

        //文件名：uuid.扩展名
        String fileName = UUID.randomUUID().toString();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        String key = module + "/" + folder + "/" + fileName + fileExtension;

        //文件上传至阿里云
        ossClient.putObject(ossProperties.getBucketname(), key, inputStream);

        // 关闭OSSClient。
        ossClient.shutdown();

        //返回url地址
        //https://guli-file-191125.oss-cn-beijing.aliyuncs.com/avatar/default.jpg
        return "https://" + bucketname + "." + endpoint + "/" + key;

    }

    @Override
    public void removeFile(String url) {
        //读取配置信息
        String endpoint = ossProperties.getEndpoint();
        String keyid = ossProperties.getKeyid();
        String keysecret = ossProperties.getKeysecret();
        String bucketname = ossProperties.getBucketname();

        //判断oss实例是否存在：如果不存在则创建，如果存在则获取
        OSS ossClient = new OSSClientBuilder().build(endpoint, keyid, keysecret);

        // 删除文件。
        //https://guli-file-221110.oss-cn-hangzhou.aliyuncs.com/
        String host = "https://" +bucketname+"."+endpoint+"/";
        String objectName = url.substring(host.length());
        ossClient.deleteObject(bucketname,objectName);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}