package com.zjl.org.conterllor;

import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.org.bean.SysDictionaryType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/org/SysDictionaryTypeService")
public class SysDictionaryTypeConterllor extends BaseConterllor<SysDictionaryType> {

}