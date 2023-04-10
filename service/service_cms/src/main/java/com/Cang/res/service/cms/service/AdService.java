package com.Cang.res.service.cms.service;

import com.Cang.res.service.cms.entity.Ad;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 广告推荐 服务类
 * </p>
 *
 * @author Cang
 * @since 2023-04-10
 */
public interface AdService extends IService<Ad> {

    List<Ad> selectByAdTypeId(String adTypeId);
}
