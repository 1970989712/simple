package com.zjl.org.service.impl;

import com.zjl.comp.bean.User;
import com.zjl.comp.service.BasicBeanService;
import com.zjl.comp.util.SpringUtil;
import com.zjl.comp.util.TreeJson;
import com.zjl.org.bean.SysMenu;
import com.zjl.org.service.SysMenuService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component("orgSysMenu")
public class SysMenuServiceImpl extends BasicBeanService<SysMenu> implements SysMenuService {

        @Override
        public Map founndMenu(String userId,String parentId) throws Exception {
            List<SysMenu> menuData =  getMenusbyUserId(userId,"MENU");
            List<SysMenu> authoritymenus = getMenusbyUserId(userId,"BUTTON");
            Map returnmap = new HashMap();
            List<Map> mapview = new ArrayList<>();
            for(SysMenu bean:menuData){  //格式化菜单
                Map beanmap = new HashMap();
                Map meta = new HashMap();
                meta.put("icon",bean.getIcon());
                meta.put("title",bean.getTitle() );
                meta.put("subTitle",bean.getSubTitle());
                meta.put("hiddenHeaderContent",bean.getHiddenHeaderContent());
                meta.put("keepAlive",bean.getKeepAlive());
                beanmap.put("menuId",bean.getMenuId());
                beanmap.put("redirect",bean.getRedirect());
                beanmap.put("hidden",bean.getHidden());
                beanmap.put("menuName",bean.getMenuName());
                beanmap.put("parentId",bean.getParentId());
                beanmap.put("path",bean.getPath());
                beanmap.put("component", bean.getComponent());
                beanmap.put("subTitle",bean.getSubTitle());
                beanmap.put("meta",meta);
                mapview.add(beanmap);
            }
            if(null==parentId||""==parentId) parentId = "0";
            TreeJson tree = new TreeJson();
            tree.setChildCode("menuId");
            List<Map> viewmenus  = tree.getzTreeData(mapview,parentId);
            returnmap.put("viewmenus",viewmenus);
            returnmap.put("authoritymenus",authoritymenus);
            return returnmap;
        }

        private List<SysMenu> getMenusbyUserId(String userId,String type) throws Exception {
            SysMenuService menuService = SpringUtil.getBean(SysMenuService.class);
            Map mm = new HashMap();
            mm.put("userId",userId);
            mm.put("menuType",type);
            List<SysMenu> menu  = menuService.queryWhere("queryMenuByUser",mm);
            return menu;
        }
}