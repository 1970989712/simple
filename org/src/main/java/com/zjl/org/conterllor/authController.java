package com.zjl.org.conterllor;

import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.bean.User;
import com.zjl.comp.util.*;
import com.zjl.comp.message.sendMessage;
import com.zjl.org.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhoujl
 * @date 2020/3/23
 */
@RestController
@RequestMapping("/org")
public class authController {

    @Resource
    private UserService userService;
    @Resource
    private AddressUtils addressUtils;


    @RequestMapping("/login")
    public Map login(@RequestBody Map map, HttpServletRequest request) throws Exception {
        Map hashmap = userService.login((String) map.get("userAccount"), (String) map.get("userPassword"));
        new Thread(() -> {
            try {
                String addr = addressUtils.getAddresses(IpUtil.getIpAddr(request));
                map.clear();
                map.put("addr", addr);
                map.put("mailbox", hashmap.get("mailbox"));
                SpringUtil.getBean(sendMessage.class).process(map);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }).start();
        return hashmap;
    }

    @RequestMapping("/logout")
    public ZlJson logout(HttpServletRequest request) {
        String userId = LoginInfo.getUser().getUserId();
        return userService.logout(userId);
    }

    @ZjlJson
    @RequestMapping("/user/info")
    public Map info(HttpServletRequest request) throws Exception {
        return userService.getInfo();
    }
}
