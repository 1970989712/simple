package com.zjl.comp.util;


import com.zjl.comp.bean.IBomfBean;

import javax.servlet.http.HttpServletRequest;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CompUtil {

    public static String addString(String... strs){
        String requststr = "";
        for (String str : strs) {
            requststr+=".";
        }
        requststr = requststr.replace(".","");
        return requststr;
    }


    /**
     * 下划线转驼峰法
     * @param line 源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern= Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }
    /**
     * 驼峰法转下划线
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }


    /**
     * 首字母大写
     * @param letter
     * @return
     */
    public static String upperFirstLatter(String letter){
        char[] chars = letter.toCharArray();
        if(chars[0]>='a' && chars[0]<='z'){
            chars[0] = (char) (chars[0]-32);
        }
        return new String(chars);
    }



    /**
     * 工具类取参
     * @param request
     * @return
     */
    public static Map getParameterMap(HttpServletRequest request){
        Map<String,String[]> map = new HashMap<String,String[]>();
        Map<String,Object> returnMap = new HashMap<String,Object>();
        map = request.getParameterMap();
        Iterator entries = map.entrySet().iterator();
        Map.Entry entry;
        String name ="";
        String value=null;
        while (entries.hasNext()){
            entry=(Map.Entry)entries.next();
            name = (String) entry.getKey();
            Object objvalue = entry.getValue();
            if(objvalue == null){
                value = null;
            }else if(objvalue instanceof String[]){
                /**条件如果成立，objvalue就是一个数组，需要将它转换成为字符串，并拼接上逗号，并吧末尾的逗号去掉*/
                String[] values = (String[]) objvalue;
                for(int i=0;i<values.length;i++){
                    value = values[i]+",";//这里我拼接的是英文的逗号。
                }
                value = value.substring(0,value.length()-1);//截掉最后一个逗号。
            }else{
                value = objvalue.toString();
            }
            returnMap.put(name , value);
        }
        Iterator it = returnMap.keySet().iterator();
        while (it.hasNext()){
            Object key = it.next();
            if(returnMap.get(key) == null || "".equals (((String)returnMap.get(key)).trim())){
                returnMap.put((String) key, null);
            }
        }
        return returnMap;
    }


    //把JavaBean转化为map
    public static <T extends IBomfBean>List<Map> bean2map(List<T> beans) throws Exception{
        List<Map> re = new ArrayList<>();
        for(Object bean:beans){
            Map<String,Object> map = new HashMap<>();
            //获取JavaBean的描述器
            BeanInfo b = Introspector.getBeanInfo(bean.getClass(),Object.class);
            //获取属性描述器
            PropertyDescriptor[] pds = b.getPropertyDescriptors();
            //对属性迭代
            for (PropertyDescriptor pd : pds) {
                //属性名称
                String propertyName = pd.getName();
                //属性值,用getter方法获取
                Method m = pd.getReadMethod();
                Object properValue = m.invoke(bean);//用对象执行getter方法获得属性值
                //把属性名-属性值 存到Map中
                map.put(propertyName, properValue);
            }
            re.add(map);
        }
        return re;
    }

        /**
         *
         * 将map转换为对象
         */
    public static <T> T mapToObject(Map<String, Object> model, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            //clazz.getDeclaredFields()获取到扩展类的所有属性,包含private属性
            Field[] fields = clazz.getDeclaredFields();
            if (fields != null && fields.length > 0) {
                //遍历所有属性
                for (Field field : fields) {
                    //通过属性的名称去Map中取数据
                    Object fieldValue = model.get(field.getName());
                    if (fieldValue != null) {
                        //如果数据存在的话,就判断数据类型是否一致,一致就将值设置到t对象中
                        //从而实现赋值操作
                        if (field.getType() == fieldValue.getClass()) {
                            field.setAccessible(true);
                            field.set(t, fieldValue);
                        }
                    }
                }
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }



    /**
     * 判断是否为基本数据类型
     */
    public static boolean isWrapClass(Class clazz) {
        try {
            if(clazz.equals(String.class)) return true;    //string 处理掉
            return ((Class) clazz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) {
            return false;
        }

    }

}
