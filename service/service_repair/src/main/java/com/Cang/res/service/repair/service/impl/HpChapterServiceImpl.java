package com.Cang.res.service.repair.service.impl;

import com.Cang.res.service.repair.entity.HpChapter;
import com.Cang.res.service.repair.entity.HpResource;
import com.Cang.res.service.repair.entity.vo.ChapterVo;
import com.Cang.res.service.repair.mapper.HpChapterMapper;
import com.Cang.res.service.repair.service.HpChapterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源 服务实现类
 * </p>
 *
 * @author Cang
 * @since 2023-03-11
 */
@Service
public class HpChapterServiceImpl extends ServiceImpl<HpChapterMapper, HpChapter> implements HpChapterService {

    @Override
    public boolean removeChapterById(String id) {
        // 资源信息
        // 删除资源
        return this.removeById(id);
    }

    // 获取资源链接的ID
    @Override
    public HpChapter getResourceById(String id) {
        HpChapter chapter = baseMapper.getResourceById(id);
        return chapter;
    }

    @Override
    public List<ChapterVo> nestedList(String resourceId) {

        List<ChapterVo> chapterVoList = new ArrayList<>();

        // 方案1：效率低  1+n个sql
        // 通过course_id获取章节列表信息：List<Chapter>  sql
        // 遍历List<Chapter>{ n
        //    通过chapter_id查询List<Video> sql
        // }

        // 方案2：效率高 1+1个sql
        // 通过course_id获取章节列表信息：List<Chapter>  sql
        // 通过course_id查询List<Video> sql

        // 获取章节信息列表
        QueryWrapper<HpChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("resource_id",resourceId);
        List<HpChapter> chapterList = baseMapper.selectList(chapterQueryWrapper);


        // 填充列表数据：Chapter列表
        // 组装章节列表：List<ChapterVo>
        for (int i = 0; i < chapterList.size(); i++) {
            HpChapter chapter = chapterList.get(i);

            // 创建ChapterVo对象
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter,chapterVo);
            chapterVoList.add(chapterVo);

            //for (VideoVo videoVo : videoVoList) {
            //    System.out.println("数据库的值" + videoVo);
            //}
        }
        return chapterVoList;
    }
}
