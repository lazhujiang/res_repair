package com.Cang.res.service.repair.service;

import com.Cang.res.service.repair.entity.HpChapter;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
