package com.zjl.org.conterllor;

import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.org.bean.SysUserGroupRole;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/org/SysUserGroupRoleService")
public class SysUserGroupRoleConterllor extends BaseConterllor<SysUserGroupRole> {

}