package com.zjl.codetool.service;

import com.zjl.codetool.util.ZlJson;
import java.util.List;
import java.util.Map;

public interface CodeService {

    /**
     * @return 数据库所有表名及描述
     */
    List<Map<String,Object>> getTableNames();


    /**
     * 创建文件
     * @param configMap 参数信息
     */
    ZlJson wirteNewCode(Map configMap) throws Exception;


    /**
     * @return 获取所有模块名称
     */
    List<String> getModules() throws Exception;

}
