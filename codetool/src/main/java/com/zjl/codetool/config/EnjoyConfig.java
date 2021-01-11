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
        engine.setToClassPathSourceFactory();
        engine.addSharedMethod(new StrKit());    //首字母大写
        //代码生成模板
        engine.addSharedFunction("/template/Bean.java");  //添加模板
        engine.addSharedFunction("/template/Dao.java");   //添加模板
        engine.addSharedFunction("/template/Service.java");  //添加模板
        engine.addSharedFunction("/template/Serviceimpl.java");   //添加模板
        engine.addSharedFunction("/template/Conterllor.java");  //添加模板
        engine.addSharedFunction("/template/Bean.xml");  //添加模板
        //代码生成界面
        engine.addSharedFunction("/static/index.html");
        // 配置极速模式，性能提升 13%
        engine.setFastMode(true);
        return jfr;
    }
}
