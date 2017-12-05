package com.scoprion.intercepter;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.scoprion.annotation.Access;
import com.scoprion.enums.CommonEnum;
import com.scoprion.result.BaseResult;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.Charset;

/**
 * 拦截器
 * Created on 2017/9/25.
 */
public class MallInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MallInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            //判断校验注解
            Access access = ((HandlerMethod) handler).getMethodAnnotation(Access.class);
            
            if (null != access) {
                if (access.need()) {
                    //获取头部信息
                    String headerAuth = request.getHeader("auth");
                    if (StringUtils.isEmpty(headerAuth)) {
                        response.getWriter().write(JSON.toJSONString(BaseResult.parameterError()));
                        return false;
                    }
                    //MD5加密
                    byte[] bytes = (CommonEnum.AUTH.getDesc() + CommonEnum.SALT.getDesc()).getBytes(
                            Charset.forName("UTF-8"));
                    String MD5 = DigestUtils.md5Hex(bytes);
                    byte[] sourceBytes = (headerAuth.trim() + CommonEnum.SALT.getDesc()).getBytes(
                            Charset.forName("UTF-8"));
                    String sourceMd5 = DigestUtils.md5Hex(sourceBytes);
                    if (StringUtils.equals(MD5, sourceMd5)) {
                        return true;
                    }
                    response.getWriter().write(JSON.toJSONString(BaseResult.authorizationError()));
                    return false;
                }
            }

        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {


    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {


    }
}
