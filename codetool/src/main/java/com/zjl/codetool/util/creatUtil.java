package com.zjl.codetool.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhoujl  工具类
 * @date 2020/9/27
 */
public class creatUtil {


    /**
     * 字段类型处理 mysqlTojdbctype,mysqlTojavatype,数据库字段转驼峰命名
     * @param list 数据表字段
     */
    public static List mysqltojavaBean(List<Map> list) {
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


    /**
     * 动态刷新修改的模板
     */
    public static void refuseInfo(){

    }


    /**
     * 创建不存在的目录
     * @param makePath 地址
     */
    public static void mkdirs(String makePath){
        File dir = new File(makePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }


}
