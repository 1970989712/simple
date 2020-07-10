package com.zjl.org.Interceptor;

import com.zjl.comp.anno.AuthorityKey;
import com.zjl.comp.bean.User;
import com.zjl.comp.exception.MyException;
import com.zjl.comp.util.RedisServiceUnit;
import com.zjl.comp.util.ResultCode;
import com.zjl.comp.util.SpringUtil;
import com.zjl.org.bean.SysMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujl
 * @date 2020/3/24
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {

    private Logger log = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Object obj = request.getAttribute("notAnony");
        boolean returnFlag = false;
        if(obj==null) return true;
        if((Boolean) obj){
            User user = (User) request.getAttribute("loginUser");
            if(user==null) throw new MyException(ResultCode.USER_ACCOUNT_EXPIRED);
            if(handler instanceof HandlerMethod) {
                String AuthorityName = "";
                HandlerMethod hand = (HandlerMethod)handler;
                if(hand.getBean().getClass().isAnnotationPresent(AuthorityKey.class)) {
                    AuthorityName+=hand.getBean().getClass().getAnnotation(AuthorityKey.class).value();
                }else {
                    log.info(hand.getBean().getClass().toString()+"缺少AuthorityKey注解");
                    return true;
                }
                if(hand.getMethod().isAnnotationPresent(AuthorityKey.class)){
                    AuthorityName+=":"+hand.getMethodAnnotation(AuthorityKey.class).value();
                }else{
                    log.info(hand.getBean().getClass().toString()+"类,"+hand.getMethod().getName()+"方法缺少AuthorityKey注解");
                    return true;
                }
                returnFlag = chackAuthority(AuthorityName,user);
                if(!returnFlag) throw new MyException(ResultCode.NO_PERMISSION);
            }
        }
        return returnFlag;
    }


    private boolean chackAuthority(String AuthorityName,User user){
        boolean flag = false;
        List<SysMenu> mymenus = (List<SysMenu>) SpringUtil.getBean(RedisServiceUnit.class).hmGet(user.getUserId(), "authoritymenus");
        for(SysMenu bean:mymenus){
            if(bean.getAuthorityKey().equals(AuthorityName)) {
                flag = true;
                break;
            }
        }
        return flag;
    }
}
