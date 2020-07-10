package com.zjl.org.conterllor;

import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.org.bean.SysDictionary;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/org/SysDictionaryService")
public class SysDictionaryConterllor extends BaseConterllor<SysDictionary> {

}