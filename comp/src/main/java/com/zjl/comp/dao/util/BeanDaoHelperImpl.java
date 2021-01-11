package com.zjl.comp.dao.util;

import com.zjl.comp.bean.IBomfBean;
import com.zjl.comp.dao.IBeanDao;
import com.zjl.comp.util.SpringUtil;
import org.springframework.stereotype.Component;

/**
 * @author zhoujl
 * @date 2020/8/27
 */
@Component
public class BeanDaoHelperImpl implements BeanDaoHelper{

    public  <T extends IBomfBean> IBeanDao<T> getBeanDao(Class<T> clazz) {
        return (IBeanDao) SpringUtil.getBean(clazz.getName() + "Dao");
    }

}
