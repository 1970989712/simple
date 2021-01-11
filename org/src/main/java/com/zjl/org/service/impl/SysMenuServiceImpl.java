package com.zjl.org.service.impl;

import com.zjl.comp.bean.User;
import com.zjl.comp.service.BasicBeanService;
import com.zjl.comp.util.SpringUtil;
import com.zjl.comp.util.TreeJson;
import com.zjl.org.bean.SysMenu;
import com.zjl.org.bean.SysResourceAuthority;
import com.zjl.org.service.SysMenuService;
import com.zjl.org.service.SysResourceAuthorityService;
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
            Map mm = new HashMap();
            mm.put("userId",userId);
            mm.put("menuType",type);
            List<SysMenu> menu  = SpringUtil.getBean(SysMenuService.class).queryWhere("queryMenuByUser",mm);
            return menu;
        }

        //获得所有菜单及角色下菜单权限
        @Override
        public Map getAllMenu(String roleId) throws Exception {
            Map remap = new HashMap();
            List<SysMenu> allmenu = SpringUtil.getBean(SysMenuService.class).queryWhere(null); //所有
            TreeJson tree = new TreeJson();
            tree.setChildCode("menuId");
            List<Map> treeData  = tree.getTreeDataByBean(allmenu,"0");
            Map mm = new HashMap();
            mm.put("roleId",roleId);
            List<SysResourceAuthority> rolemenu = SpringUtil.getBean(SysResourceAuthorityService.class).queryWhere(mm);
            List<String> checkedKeys = new ArrayList<>();
            rolemenu.forEach(e->{checkedKeys.add(e.getResourceId());});
            remap.put("treeData",treeData);
            remap.put("checkedKeys",checkedKeys);
            return remap;
        }
}