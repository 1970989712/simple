package com.zjl.codetool.conterllor;

import com.zjl.codetool.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoujl
 * @date 2020/9/27
 */
@Controller
public class CdxConterllor {

    @Autowired
    private FileService service;


    @RequestMapping
    public void initFun(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getRequestDispatcher("/codetool/index").forward(request,response);
    }

    @ResponseBody
    @RequestMapping("/getFile")
    public String getFile(String templateType) throws Exception {
        return service.readFile(templateType);
    }


    @ResponseBody
    @RequestMapping(value = "/changeFile",method= RequestMethod.POST)
    public String changeFile(String templateType, String templateText) throws Exception {
        return service.changeFile(templateType,templateText);
    }



}
