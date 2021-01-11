package com.zjl.org.conterllor;

import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.comp.util.SpringUtil;
import com.zjl.org.bean.SysMenu;
import com.zjl.org.service.SysMenuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/org/SysMenuService")
public class SysMenuConterllor extends BaseConterllor<SysMenu> {

    @ZjlJson
    @RequestMapping("/getRoleMenuTree")
    public Map getRoleMenuTree(String roleId) throws Exception {
        return SpringUtil.getBean(SysMenuService.class).getAllMenu(roleId);
    }

}