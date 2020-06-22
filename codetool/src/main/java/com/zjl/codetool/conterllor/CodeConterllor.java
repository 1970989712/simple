package com.zjl.codetool.conterllor;

import com.zjl.codetool.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhoujl
 * @date 2020/5/1
 */

@RestController
@RequestMapping("/codetool")
public class CodeConterllor {

    @Autowired
    private CodeService service;

    @RequestMapping("/wirte")
    public void wirteCode() throws Exception {
        service.wirteCode();
    }


    @RequestMapping("/test1")
    public ModelAndView test1(HttpServletResponse response, HttpServletRequest request){
        // 设置头部信息返回即可下载
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=enjoy.text");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ModelAndView mv = new ModelAndView();
        mv.addObject("test","张三");
        mv.setViewName("/view/index.html");
        return mv;
    }

}
