package #(pageName).dao;

import com.zjl.comp.dao.BasicBeanDao;
import #(pageName).bean.#(beanName);
import org.springframework.stereotype.Component;

@Component
public class #(beanName)Dao extends BasicBeanDao<#(beanName)> {

}
