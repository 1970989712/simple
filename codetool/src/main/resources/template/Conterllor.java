package #(pageName).conterllor;

import com.zjl.comp.conterllor.BaseConterllor;
import #(pageName).bean.#(beanName);
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/#(module)/#(beanName)Service")
public class #(beanName)Conterllor extends BaseConterllor<#(beanName)> {

}
