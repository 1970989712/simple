package com.zjl.comp.config;


import com.alibaba.fastjson.JSON;
import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.util.ZlJson;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回数据前数据处理
 * user zjl
 * date 2019/12/13
 */
@RestControllerAdvice
public class advicebefor implements ResponseBodyAdvice {

    /**
     * 判断哪些需要拦截
     * @param methodParameter
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        Boolean flag = false;
        if (methodParameter.getMethod().isAnnotationPresent(ZjlJson.class)) flag=true;
        return flag;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        ZlJson json = new ZlJson(o);
        if(o instanceof String){    //string 特殊处理 包装成一个String类型的对象
            return JSON.toJSONString(json);
        }
        return json;
    }
}
