package com.zjl.codetool.config;

import com.jfinal.kit.StrKit;
import com.jfinal.template.Engine;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhoujl
 * @date 2020/5/1
 */
@Configuration
public class EnjoyConfig {

    @Bean(name = "jfinalViewResolver")
    public JFinalViewResolver getJFinalViewResolver() {
        JFinalViewResolver jfr = new JFinalViewResolver();
        Engine engine = jfr.engine;  //使用Enjoy模板
        engine.setDevMode(true);
        String dirPath = System.getProperty("user.dir"); //获取项目文件地址
        String codeTemplatePath = "\\codetool\\src\\main\\resources\\template"; //模板地址;
        engine.setBaseTemplatePath(dirPath + codeTemplatePath);
        engine.addSharedMethod(new StrKit());    //首字母大写
        //代码生成模板
        engine.addSharedFunction("/Bean.java");  //添加模板
        engine.addSharedFunction("/Dao.java");   //添加模板
        engine.addSharedFunction("/Service.java");  //添加模板
        engine.addSharedFunction("/Serviceimpl.java");   //添加模板
        engine.addSharedFunction("/Conterllor.java");  //添加模板
        engine.addSharedFunction("/Bean.xml");  //添加模板
        //代码生成界面
        engine.addSharedFunction("/view/index.html");  //添加查看页面
        // 配置极速模式，性能提升 13%
        engine.setFastMode(true);
        return jfr;
    }
}
