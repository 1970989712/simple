package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_menu")
@MapperXml(namespace="SysMenu")
public class SysMenu extends AbstractBean {

    /** 菜单id **/
    private String menuId;

    /** 菜单name **/
    private String menuName;

    /** 菜单title **/
    private String title;

    /** path **/
    private String path;

    /** 组件地址 **/
    private String component;

    /** 跳转地址 **/
    private String redirect;

    /** 是否显示 **/
    private Boolean hidden;

    /** 是否启用 **/
    private Boolean enable;

    /** 总是显示根路由 **/
    private Boolean alwaysShow;

    /** 是否在面包屑中显示 **/
    private Boolean breadcrumb;

    /** 菜单类型 **/
    private String menuType;

    /** 权限标识 **/
    private String authorityKey;

    /** 菜单排序 **/
    private Integer sortNo;

    /** 菜单图标 **/
    private String icon;

    /** 父菜单id **/
    private String parentId;

    /** 创建人 **/
    private String createUser;

    /** 创建时间 **/
    private Date createTime;

    /** 修改人 **/
    private String updateUser;

    /** 修改时间 **/
    private Date updateTime;



    public  String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }


    public  String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }


    public  String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public  String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    public  String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }


    public  String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }


    public  Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }


    public  Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public  Boolean getAlwaysShow() {
        return alwaysShow;
    }

    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
    }


    public  Boolean getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(Boolean breadcrumb) {
        this.breadcrumb = breadcrumb;
    }


    public  String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }


    public  String getAuthorityKey() {
        return authorityKey;
    }

    public void setAuthorityKey(String authorityKey) {
        this.authorityKey = authorityKey;
    }


    public  Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    public  String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public  String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public  String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public  String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    public  Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
