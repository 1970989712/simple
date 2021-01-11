package com.zjl.codetool.conterllor;

import com.zjl.codetool.service.CodeService;
import com.zjl.codetool.util.CompUtil;
import com.zjl.codetool.util.ZlJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * @author zhoujl
 * @date 2020/5/1
 */

@Controller
@RequestMapping("/codetool")
public class CodeConterllor {

    @Autowired
    private CodeService service;

    @RequestMapping("/index")
    public ModelAndView index() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Map<String,Object>> mapL = service.getTableNames();
        List<String> modules = service.getModules();
        mv.addObject("menus",mapL);
        mv.addObject("modules",modules);
        mv.setViewName("/static/index.html");
        return mv;
    }


    @ResponseBody
    @RequestMapping(value = "/wirte",method= RequestMethod.POST)
    public ZlJson wirteCode(HttpServletRequest request) throws Exception {
        return service.wirteNewCode(CompUtil.getParameterMap(request));
    }

}
