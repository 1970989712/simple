package com.zjl.org.conterllor;

import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.org.bean.#(beanName);
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/org/#(beanName)Service")
public class #(beanName)Conterllor extends BaseConterllor<#(beanName)> {

}
