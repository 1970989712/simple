package com.zjl.org.service.impl;

import com.zjl.comp.bean.User;
import com.zjl.comp.exception.MyException;
import com.zjl.comp.service.BasicBeanService;
import com.zjl.comp.util.*;
import com.zjl.org.bean.SysUser;
import com.zjl.org.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("orgUserService")
public class UserServiceImpl extends BasicBeanService<SysUser> implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RedisServiceUnit redisUnit;

    @Override
    public Map login(String account,String password) throws Exception {
        boolean flag = false;
        if(null==account||""==account) throw new MyException("参数传递异常!");
        Map m = new HashMap<>();
        m.put("account",account);
        SysUser user = this.queryOne(m);
        User loginuser = CompUtil.mapToObject(BeanMap.create(user),User.class);
        m.clear();
        if(user!=null) flag =  bCryptPasswordEncoder.matches(password, user.getPassword());
        m.put("status",flag);
        if(flag){
        //  List<SysPermission> lists = permissionService.getAllData();
          String token = JwtTokenUtils.createToken(user.getUserId());
          redisUnit.hmSet(user.getUserId(),"user",loginuser);
          redisUnit.hmSet(user.getUserId(),"token",token);
          m.put("mailbox",user.getMailbox());
          m.put("code","200");
          m.put("data",token);
          user.setLastLoginTime(new Date());
          this.update(user);
        }
        return m;
    }

    @Override
    public ZlJson logout(String userId){
        redisUnit.remove(userId);
        return new ZlJson();
    }

    @Override
    public Map getInfo(){
        Map map = new HashMap();
        User user =  (User)redisUnit.hmGet(LoginInfo.getUser().getUserId(),"user");
        map.put("user",user);
        return map;
    }


}
