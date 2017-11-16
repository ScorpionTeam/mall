package com.scoprion.mall.domain.analyze;

import java.util.Date;

/**
 * 访问分析
 * @author by kunlun
 * @created on 2017/11/16.
 */
public class VisitTrend {

    /**
     * 时间
     */
    private String ref_date;

    /**
     * 打开次数
     */
    private int session_cnt;

    /**
     * 访问人数
     */
    private int visit_uv;

    /**
     * 新用户数
     */
    private int visit_uv_new;

    /**
     * 人均停留时长
     */
    private float stay_time_uv;

    /**
     * 次均停留时长
     */
    private float stay_time_session;

    /**
     * 平均访问深度
     */
    private float visit_depth;
}
