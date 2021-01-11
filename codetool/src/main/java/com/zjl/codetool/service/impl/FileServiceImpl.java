package com.zjl.codetool.service.impl;

import com.zjl.codetool.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @author zhoujl
 * @date 2020/9/28
 */
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String readFile(String templateType) throws IOException {
       InputStream in = this.getClass().getResourceAsStream("/template/"+templateType);
       byte[] bytes =  new byte[in.available()];
       in.read(bytes);
       return new String(bytes);
    }

    @Override
    public String changeFile(String templateType,String templateText) throws IOException {
        File file = new File("codetool/src/main/resources/template/"+templateType);
        FileUtils.write(file, templateText, "utf-8");
        return "aaa";
    }


}
