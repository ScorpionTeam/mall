package com.scoprion.mall.backstage.service.activity;

import com.scoprion.mall.domain.Activity;
import com.scoprion.result.BaseResult;
import com.scoprion.result.PageResult;

/**
 * @author by kunlun
 * @created on 2017/11/1.
 */
public interface ActivityService {


    /**
     * 创建活动
     * @param activity
     * @return
     */
    BaseResult add(Activity activity);

    /**
     * 修改活动
     * @param activity
     * @return
     */
    BaseResult update(Activity activity);

    /**
     * 删除活动
     * @param id
     * @return
     */
    BaseResult deleteById(Long id);

    /**
     * 查询活动
     * @param pageNo
     * @param pageSize
     * @param searchKey
     * @return
     */
    PageResult findByCondition(int pageNo,int pageSize,String searchKey);

}
