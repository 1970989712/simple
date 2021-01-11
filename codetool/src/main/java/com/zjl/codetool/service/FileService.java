package com.zjl.codetool.service;

import java.io.IOException;

/**
 * @author zhoujl
 * @date 2020/9/28
 */
public interface FileService {

    /**
     * 读取模板信息
     * @param templateType 模板
     */
    String readFile(String templateType) throws IOException;

    /**
     * 修改模板
     * @param templateType 模板
     * @param templateText 修改后的模板字符串
     */
    String changeFile(String templateType, String templateText) throws IOException;
}
