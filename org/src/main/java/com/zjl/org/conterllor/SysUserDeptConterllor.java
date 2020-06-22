package com.zjl.org.conterllor;

import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.org.bean.SysUserDept;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/org/SysUserDeptService")
public class SysUserDeptConterllor extends BaseConterllor<SysUserDept> {

}