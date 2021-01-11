package com.zjl.org.conterllor;

import com.alibaba.excel.EasyExcel;
import com.zjl.comp.anno.AuthorityKey;
import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.comp.exception.MyException;
import com.zjl.comp.util.ExcelListener;
import com.zjl.comp.util.SpringUtil;
import com.zjl.comp.util.ZlJson;
import com.zjl.org.bean.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/org/UserService")
@AuthorityKey("org:UserService")
public class UserConterllor extends BaseConterllor<SysUser> {



    @RequestMapping("/importExcel")
    public void importExcel(@RequestParam("file") MultipartFile file) throws Exception {
        ExcelListener excelListener = new ExcelListener<SysUser>();
        EasyExcel.read(new BufferedInputStream(file.getInputStream()),excelListener).head(SysUser.class).sheet().doReadSync();
    }


}
