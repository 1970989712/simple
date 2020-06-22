package com.zjl.codetool.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.template.Engine;
import com.jfinal.template.Template;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.zjl.codetool.service.CodeService;
import com.zjl.comp.util.CompUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujl
 * @date 2020/4/30
 */
@Service
public class CodeServiceImpl implements CodeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JFinalViewResolver jfr;

    @Override
    public void wirteCode() throws Exception {
        String db_table_name = "sys_user_dept";
        String dirPath = System.getProperty("user.dir"); //获取项目文件地址
        String path = "\\org\\src\\main\\java\\com\\zjl\\org";        //指定文件生成路径

        Map map = getTableName(db_table_name);
        if (null == map && map.size() == 0) throw new RuntimeException("数据库表不存在");
        String tableName = (String) map.get("tableName");
        List lists = getcolumn(tableName);
        if (null == lists && lists.size() == 0) throw new RuntimeException("字段查询不存在");

        Engine engine = jfr.engine;
        Template beanTemplate = engine.getTemplate("/Bean.java"); //获取模板
        Template daoTemplate = engine.getTemplate("/Dao.java"); //获取模板
        Template serviceTemplate = engine.getTemplate("/Service.java"); //获取模板
        Template serviceImplTemplate = engine.getTemplate("/Serviceimpl.java"); //获取模板
        Template conterllorTemplate =engine.getTemplate("/Conterllor.java"); //获取模板
        Template beanxmlTemplate = engine.getTemplate("/Bean.xml"); //获取模板

        createBeanJava(db_table_name, lists, beanTemplate, dirPath + path);   //生成java文件
        createDaoJava(db_table_name, daoTemplate, dirPath + path);
        createServiceJava(db_table_name, serviceTemplate, dirPath + path);
        createServiceImplJava(db_table_name, serviceImplTemplate, dirPath + path);
        createConterllorJava(db_table_name, conterllorTemplate, dirPath + path);
        createMybaatisBeanXml(db_table_name, lists, beanxmlTemplate, dirPath + "\\org\\src\\main\\resources");
    }

    /**
     * 获取数据所有表信息
     *
     * @param name
     * @return
     */
    private Map getTableName(String name) {
        String sql = "select table_name as tableName,table_comment as tableComment from information_schema.tables where table_schema='2020simple' and table_type='base table' and table_name='" + name + "'";
        Map map = jdbcTemplate.queryForMap(sql);
        return map;
    }

    /**
     * 获取表中字段信息
     *
     * @param tableName
     * @return
     */
    private List getcolumn(String tableName) {
        StringBuffer strbuff = new StringBuffer();
        strbuff.append("select column_name as columnName,data_type as dataType,COLUMN_comment as columnComment");
        strbuff.append(" from information_schema.columns");
        strbuff.append(" where table_schema='2020simple' and table_name='" + tableName + "'");
        return jdbcTemplate.queryForList(strbuff.toString());
    }

    /**
     * 生成实体类
     *
     * @param tableName
     * @param list
     * @param template
     * @param path
     * @throws Exception
     */
    private void createBeanJava(String tableName, List list, Template template, String path) throws Exception {
        Kv kv = Kv.by("tablist", mysqltojavaBean(list));
        String beanName = CompUtil.underline2Camel(tableName, true);
        beanName = CompUtil.upperFirstLatter(beanName);
        kv.set("tableName", tableName);
        kv.set("beanName", beanName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.render(kv, baos);
        File file = new File(path + "\\bean\\" + beanName + ".java");
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + ".java文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + ".java文件");
        } catch (FileNotFoundException e) {
            log.debug("生成" + beanName + ".java文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }

    /**
     * 生成dao层
     *
     * @param tableName
     * @param template
     * @param path
     * @throws Exception
     */
    private void createDaoJava(String tableName, Template template, String path) throws Exception {
        String beanName = CompUtil.underline2Camel(tableName, true);
        beanName = CompUtil.upperFirstLatter(beanName);
        Kv kv = Kv.by("beanName", beanName);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.render(kv, baos);

        File file = new File(path + "\\Dao\\" + beanName + "Dao.java");
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + "Dao.java文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + "Dao.java文件");
        } catch (FileNotFoundException e) {
            log.debug("生成" + beanName + "Dao.java文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }

    /**
     * 生成service接口
     *
     * @param tableName
     * @param template
     * @param path
     * @throws Exception
     */
    private void createServiceJava(String tableName, Template template, String path) throws Exception {
        String beanName = CompUtil.underline2Camel(tableName, true);
        beanName = CompUtil.upperFirstLatter(beanName);
        Kv kv = Kv.by("beanName", beanName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.render(kv, baos);
        File file = new File(path + "\\service\\" + beanName + "Service.java");
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + "service.java文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + "service.java文件");
        } catch (FileNotFoundException e) {
            log.debug("生成" + beanName + "service.java文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }

    /**
     * 生成service 实现类
     * @param tableName
     * @param template
     * @param path
     * @throws Exception
     */
    private void createServiceImplJava(String tableName, Template template, String path) throws Exception {
        String beanName = CompUtil.underline2Camel(tableName, true);
        beanName = CompUtil.upperFirstLatter(beanName);
        Kv kv = Kv.by("beanName", beanName);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.render(kv, baos);

        File file = new File(path + "\\service\\impl\\" + beanName + "ServiceImpl.java");
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + "ServiceImpl.java文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + "ServiceImpl.java文件");
        } catch (FileNotFoundException e) {
            log.debug("生成" + beanName + "ServiceImpl.java文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }

    /**
     * 生成 conterllor
     *
     * @param tableName
     * @param template
     * @param path
     * @throws Exception
     */
    private void createConterllorJava(String tableName, Template template, String path) throws Exception {
        String beanName = CompUtil.underline2Camel(tableName, true);
        beanName = CompUtil.upperFirstLatter(beanName);
        Kv kv = Kv.by("beanName", beanName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.render(kv, baos);

        File file = new File(path + "\\conterllor\\" + beanName + "Conterllor.java");
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + "Conterllor.java文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + "Conterllor.java文件");
        } catch (FileNotFoundException e) {
            log.debug("生成" + beanName + "Conterllor.java文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }

    /**
     * 生成bean-xml
     *
     * @param tableName
     * @param list
     * @param template
     * @param path
     * @throws Exception
     */
    private void createMybaatisBeanXml(String tableName, List list, Template template, String path) throws Exception {
        Kv kv = Kv.by("tablist", mysqltojavaBean(list)); //处理后的实体名
        String beanName = CompUtil.underline2Camel(tableName, true);
        beanName = CompUtil.upperFirstLatter(beanName);
        kv.set("tableName", tableName);
        kv.set("beanName", beanName);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        template.render(kv, baos);
        File dir = new File(path + "\\bean\\");
        if (!dir.exists()) {// 判断目录是否存在
            dir.mkdirs();  //多层目录需要调用mkdirs
        }
        File file = new File(path + "\\bean\\" + beanName + ".xml");
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + ".xml文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + ".xml文件");
        } catch (FileNotFoundException e) {
            log.debug("生成" + beanName + ".xml文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }


    /**
     * 字段类型处理 mysqlTojdbctype,mysqlTojavatype,数据库字段转驼峰命名
     *
     * @param list
     * @return
     */
    private List mysqltojavaBean(List<Map> list) {
        List lists = new ArrayList();
        try {
            for (Map map : list) {
                String columnName = (String) map.get("columnName");
                if (columnName.indexOf("_") > 0) columnName = CompUtil.underline2Camel(columnName, true);
                String beanfiledName = columnName;
                map.put("beanfildname", beanfiledName);
                String dataType = (String) map.get("dataType");
                switch (dataType.toUpperCase()) {
                    case "TINYINT":
                        map.put("dataTypejava", "Boolean");
                        map.put("dataTypejdbc", "TINYINT");
                        break;
                    case "SMALLINT":
                        map.put("dataTypejava", "Integer");
                        map.put("dataTypejdbc", "SMALLINT");
                        break;
                    case "MEDIUMINT":
                        map.put("dataTypejava", "Integer");
                        map.put("dataTypejdbc", "MEDIUMINT");
                        break;
                    case "INT":
                        map.put("dataTypejava", "Integer");
                        map.put("dataTypejdbc", "INTEGER");
                        break;
                    case "INTEGER":
                        map.put("dataTypejava", "Integer");
                        map.put("dataTypejdbc", "INTEGER");
                        break;
                    case "BIGINT":
                        map.put("dataTypejava", "Long");
                        map.put("dataTypejdbc", "BIGINT");
                        break;
                    case "FLOAT":
                        map.put("dataTypejava", "float");
                        map.put("dataTypejdbc", "FLOAT");
                        break;
                    case "DOUBLE":
                        map.put("dataTypejava", "Double");
                        map.put("dataTypejdbc", "DOUBLE");
                        break;
                    case "DECIMAL":
                        map.put("dataTypejava", "Decimal");
                        map.put("dataTypejdbc", "DECIMAL");
                        break;
                    case "DATE":
                        map.put("dataTypejava", "Date");
                        map.put("dataTypejdbc", "Date");
                        break;
                    case "TIME":
                        map.put("dataTypejava", "Date");
                        map.put("dataTypejdbc", "TIME");
                        break;
                    case "TIMESTAMP":
                        map.put("dataTypejava", "Date");
                        map.put("dataTypejdbc", "TIMESTAMP");
                        break;
                    case "DATETIME":
                        map.put("dataTypejava", "Date");
                        map.put("dataTypejdbc", "TIMESTAMP");
                        break;
                    case "YEAR":
                        map.put("dataTypejava", "Date");
                        map.put("dataTypejdbc", "Date");
                        break;
                    case "VARCHAR":
                        map.put("dataTypejava", "String");
                        map.put("dataTypejdbc", "VARCHAR");
                    case "CHAR":
                        map.put("dataTypejava", "String");
                        map.put("dataTypejdbc", "CHAR");
                    default:
                        map.put("dataTypejava", "String");
                        map.put("dataTypejdbc", "VARCHAR");
                        break;
                }
                lists.add(map);
            }
        } catch (Exception e) {
            throw new RuntimeException("实体类类型转换异常");
        }
        return lists;
    }
}
