package #(pageName).service.impl;

import com.zjl.comp.service.BasicBeanService;
import #(pageName).bean.#(beanName);
import #(pageName).service.#(beanName)Service;
import org.springframework.stereotype.Component;

@Component("#(module)#(beanName)")
public class #(beanName)ServiceImpl extends BasicBeanService<#(beanName)> implements #(beanName)Service {

}
