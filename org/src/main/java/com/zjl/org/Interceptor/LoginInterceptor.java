package com.zjl.org.Interceptor;

import com.zjl.comp.exception.MyException;
import com.zjl.comp.util.JwtTokenUtils;
import com.zjl.comp.util.RedisServiceUnit;
import com.zjl.comp.util.ResultCode;
import com.zjl.comp.util.SpringUtil;
import com.zjl.org.bean.SysAnonymous;
import com.zjl.org.bean.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhoujl
 * @date 2020/3/24
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final String tokenType = "Access-Token";

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (chackUrl(httpServletRequest.getRequestURL().toString())) return true;
        String authorization = httpServletRequest.getHeader(tokenType);
        if (!StringUtils.isEmpty(authorization)) {
            String token = authorization;
            String userId = JwtTokenUtils.getUserid(token);
            if (userId == null) throw new MyException(ResultCode.TOKEN_NOT_USER);
            String redToken = (String) SpringUtil.getBean(RedisServiceUnit.class).hmGet(userId, "token");
            if (!redToken.equals(token)) throw new MyException(ResultCode.TOKEN_NOT_FIND);
            SysUser usr = (SysUser)SpringUtil.getBean(RedisServiceUnit.class).hmGet(userId, "user");
            httpServletRequest.setAttribute("loginUserId",userId);
            httpServletRequest.setAttribute("loginUser",usr);
            return true;
        } else {
            throw new MyException(ResultCode.NO_PERMISSION);
        }
    }

    //匿名访问清单
    private Boolean chackUrl(String url){
        List<SysAnonymous> lists = (List<SysAnonymous>) SpringUtil.getBean(RedisServiceUnit.class).get("anonymous");
        Boolean flag  = false;
        for(SysAnonymous s: lists){
            if(url.indexOf(s.getUrl())>=0) flag = true;
        }
        return flag;
    }

}
