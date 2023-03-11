package com.Cang.res.service.repair.service.impl;

import com.Cang.res.service.repair.entity.HpChapter;
import com.Cang.res.service.repair.mapper.HpChapterMapper;
import com.Cang.res.service.repair.service.HpChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
