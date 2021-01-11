package com.zjl.comp.dao.util;

import com.zjl.comp.bean.IBomfBean;
import com.zjl.comp.dao.IBeanDao;
import org.springframework.stereotype.Component;

/**
 * @author zhoujl
 * @date 2020/8/27
 */
public interface BeanDaoHelper {

    <T extends IBomfBean> IBeanDao<T> getBeanDao(Class<T> clazz);

}
