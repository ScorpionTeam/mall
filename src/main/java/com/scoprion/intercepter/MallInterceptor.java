package com.scoprion.intercepter;

import com.scoprion.annotation.AccessSecret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * Created on 2017/9/25.
 */
public class MallInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MallInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            AccessSecret accessSecret = ((HandlerMethod) handler).getMethodAnnotation(AccessSecret.class);
            if (null != accessSecret) {
                boolean visit = accessSecret.visit();
                if (!visit) {
                    LOGGER.info("无权访问");
                    response.sendRedirect("four");
                    return false;
                }
                LOGGER.info("请求1..........");
            }

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        LOGGER.info("请求2..........");

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

        LOGGER.info("请求3..........");

    }
}
