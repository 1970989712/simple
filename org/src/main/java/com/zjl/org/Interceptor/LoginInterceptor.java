package com.zjl.org.Interceptor;

import com.zjl.comp.bean.User;
import com.zjl.comp.exception.MyException;
import com.zjl.comp.util.JwtTokenUtils;
import com.zjl.comp.util.RedisServiceUnit;
import com.zjl.comp.util.ResultCode;
import com.zjl.comp.util.SpringUtil;
import com.zjl.org.bean.SysAnonymous;
import com.zjl.org.bean.SysUser;
import com.zjl.org.service.SysAnonymousService;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * info:登录校验功能
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
            User usr = (User)SpringUtil.getBean(RedisServiceUnit.class).hmGet(userId, "user");
            httpServletRequest.setAttribute("loginUserId",userId);
            httpServletRequest.setAttribute("loginUser",usr);
            return true;
        } else {
            throw new MyException(ResultCode.NO_PERMISSION);
        }
    }

    private Boolean chackUrl(String url) throws Exception {
        Boolean rerustFlag = false;
        RedisServiceUnit redisutil = SpringUtil.getBean(RedisServiceUnit.class);
        if(!redisutil.exists("anonymous"))SpringUtil.getBean(SysAnonymousService.class).getAllAnonymous();
        List<SysAnonymous> anylists = (List<SysAnonymous>) redisutil.get("anonymous");
        for(SysAnonymous bean:anylists){
            if(url.indexOf(bean.getUrl())>=0) {
                rerustFlag = true;
                break;
            }
        }
        return rerustFlag;
    }

}
