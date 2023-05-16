package com.Cang.res.service.repair.service;

import com.Cang.res.service.repair.entity.HpChapter;
import com.Cang.res.service.repair.entity.vo.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 资源 服务类
 * </p>
 *
 * @author Cang
 * @since 2023-03-11
 */
public interface HpChapterService extends IService<HpChapter> {
    boolean removeChapterById(String id);

    // 获取资源链接的ID
    HpChapter getResourceById(String id);

    List<ChapterVo> nestedList(String id);
}
