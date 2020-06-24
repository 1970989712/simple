package com.zjl.comp.util;

import com.zjl.comp.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author zhoujl
 * @date 2020/6/24
 */
@Component
public class LoginInfo {

    public static User getUser(){
        HttpServletRequest request= ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return (User)request.getAttribute("loginUser");
    }


}
