package com.zjl.org.service.impl;

import com.zjl.comp.service.BasicBeanService;
import com.zjl.org.bean.#(beanName);
import com.zjl.org.service.#(beanName)Service;
import org.springframework.stereotype.Component;

@Component("org#(beanName)")
public class #(beanName)ServiceImpl extends BasicBeanService<#(beanName)> implements #(beanName)Service {

}
