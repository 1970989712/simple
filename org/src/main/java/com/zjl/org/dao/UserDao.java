package com.zjl.org.dao;

import com.zjl.comp.dao.BasicBeanDao;
import com.zjl.comp.util.SpringUtil;
import com.zjl.org.bean.SysUnit;
import com.zjl.org.bean.SysUser;
import com.zjl.org.bean.SysUserDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserDao extends BasicBeanDao<SysUser> {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void preInsert(SysUser bean) throws Exception {
        bean.setPassword(bCryptPasswordEncoder.encode("12345"));
        super.preInsert(bean);
    }

    @Override
    public void afterInsert(String loginUserId,Map<String, Object> params, SysUser bean) throws Exception {
        Map m = (Map) params.get("extData");
        if(null!= m.get("unitId")&&""!=m.get("unitId")){
          SysUnit unit =  SpringUtil.getBean(SysUnitDao.class).queryById((String) m.get("unitId"));
          SysUserDept dept = new SysUserDept();
          dept.setUserId(bean.getUserId());
          dept.setDeptId(unit.getUnitId());
          dept.setMain(unit.getUnitType()=="main"?true:false);
          dept.setValid(true);
          SpringUtil.getBean(SysUserDeptDao.class).insert(dept);
        }
        super.afterInsert(loginUserId,params, bean);
    }

}
