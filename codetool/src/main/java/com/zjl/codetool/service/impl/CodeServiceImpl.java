package com.zjl.codetool.service.impl;

import com.jfinal.kit.Kv;
import com.jfinal.template.ext.spring.JFinalViewResolver;
import com.zjl.codetool.service.CodeService;
import com.zjl.codetool.util.ZlJson;
import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.zjl.codetool.util.creatUtil;
import java.io.*;
import java.util.List;
import java.util.Map;



/**
 * @author zhoujl
 * @date 2020/4/30
 */
@Service
public class CodeServiceImpl implements CodeService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${projectPath}")
    private String projectPath;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private JFinalViewResolver jfr;

    private static final String javapath = "\\src\\main\\java\\";

    private static final String resourcespath = "\\src\\main\\resources";


    @Override
    public List<Map<String,Object>> getTableNames(){
        String sql = "select table_name as tableName,table_comment as tableComment from information_schema.tables where table_schema='"+getDbName()+"' and (table_type='base table' or table_type='BASE TABLE') ";
        return jdbcTemplate.queryForList(sql);
    }

    @Override
    public List<String> getModules() throws Exception{
        FileInputStream fis = new FileInputStream(new File(projectPath+ "/pom.xml"));
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = reader.read(fis);
        List<String> modules = model.getModules();
        return modules;
    }


    @Override
    public ZlJson wirteNewCode(Map configMap) throws Exception {
        String dbTableName =  (String) configMap.get("selectTable");
        String beanName =  (String) configMap.get("beanName");
        String pageName = (String) configMap.get("pageName");
        String module = (String) configMap.get("modules");
        String bean = (String) configMap.get("bean");
        String xml = (String) configMap.get("xml");
        String Dao = (String) configMap.get("Dao");
        String Service = (String) configMap.get("Service");
        String Conterllor = (String) configMap.get("Conterllor");
        String moduleP = "";
        if(module!=null&&module!="") moduleP = "\\"+module;
        String pathJ = projectPath+moduleP+javapath+pageName.replace(".","\\");
        String pathR = projectPath+moduleP+resourcespath;
        List lists = getcolumn(dbTableName);
        Kv kv = init(dbTableName,beanName,pageName,module,lists);
        if (null == lists && lists.size() == 0) throw new RuntimeException("字段查询不存在");
        if(bean!=null)  createBeanJava(kv, beanName, pathJ);
        if(Dao!=null)  createDaoJava(kv, beanName, pathJ);
        if(Service!=null) {
            createServiceJava(kv, beanName, pathJ);
            createServiceImplJava(kv, beanName, pathJ);
        }
        if(Conterllor!=null)  createConterllorJava(kv, beanName, pathJ);
        if(xml!=null)  createMybaatisBeanXml(kv, beanName, pathR);
        return new ZlJson();
    }


    private Kv init(String dbTableName,String beanName,String pageName,String module,List lists){
        Kv kv = Kv.by("tablist", creatUtil.mysqltojavaBean(lists));
        kv.set("tableName", dbTableName);
        kv.set("beanName", beanName);
        kv.set("pageName", pageName);
        kv.set("module", module);
        return kv;
    }

    private void createBeanJava(Kv kv, String beanName, String path) throws Exception {
        createWork(kv,beanName,"/template/Bean.java",path + "\\bean\\","java");
    }

    private void createDaoJava(Kv kv, String beanName, String path) throws Exception {
        createWork(kv,beanName+"Dao","/template/Dao.java",path + "\\Dao\\","java");
    }

    private void createServiceJava(Kv kv, String beanName, String path) throws Exception {
        createWork(kv, beanName + "Service", "/template/Service.java", path + "\\Service\\", "java");
    }

    private void createServiceImplJava(Kv kv, String beanName, String path) throws Exception {
        createWork(kv, beanName + "ServiceImpl", "/template/Serviceimpl.java", path + "\\Service\\impl\\", "java");
    }

    private void createConterllorJava(Kv kv, String beanName, String path) throws Exception {
        createWork(kv, beanName + "Conterllor", "/template/Conterllor.java", path + "\\conterllor\\", "java");
    }

    private void createMybaatisBeanXml(Kv kv, String beanName, String path) throws Exception {
        createWork(kv, beanName, "/template/Bean.xml", path + "\\bean\\", "xml");
    }

    private void createWork(Kv kv,String beanName,String template,String specificPath,String type) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        jfr.engine.getTemplate(template).render(kv, baos);
        creatUtil.mkdirs(specificPath);
        File file = new File( specificPath+ beanName + "."+type);
        FileOutputStream fileout = null;
        try {
            log.debug("开始生成" + beanName + "."+type+"文件");
            fileout = new FileOutputStream(file);
            baos.writeTo(fileout);
            log.debug("成功生成" + beanName + "."+type+"文件");
        } catch (Exception e) {
            log.debug("生成" + beanName + "."+type+"文件失败");
            e.printStackTrace();
        } finally {
            fileout.close();
        }
    }

    private List getcolumn(String tableName) {
        StringBuffer strbuff = new StringBuffer();
        strbuff.append("select column_name as columnName,data_type as dataType,COLUMN_comment as columnComment");
        strbuff.append(" from information_schema.columns");
        strbuff.append(" where table_schema='"+getDbName()+"' and table_name='" + tableName + "'");
        return jdbcTemplate.queryForList(strbuff.toString());
    }

    private String getDbName(){
        return  (String) jdbcTemplate.queryForMap("select database();").get("database()");
    }

}
