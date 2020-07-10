package com.zjl.comp.util;

import com.zjl.comp.bean.IBomfBean;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeJson {

    private String childCode;// 子 字段

    private String parentCode;// 父 字段

    private String childName;// 子 集合名称

    public TreeJson() {
        this.childCode = "id";
        this.parentCode = "parentId";
        this.childName = "children";
    }

    public TreeJson(String childCode) {
        this.childCode = childCode;
        this.parentCode = "parentId";
        this.childName = "children";
    }

    public TreeJson(String childCode, String childName) {
        this.childCode = childCode;
        this.parentCode = "parentId";
        this.childName = childName;
    }

    public TreeJson(String childCode, String parentCode, String childName) {
        this.childCode = childCode;
        this.parentCode = parentCode;
        this.childName = childName;
    }

    public <T extends IBomfBean>List<Map> getTreeDataByBean(List<T> list, String parentId){
       return getzTreeData(CompUtil.EntityConvertMap(list),parentId);
    }


    public List<Map> getzTreeData(List<Map> allData,String pid) {
        List<Map> ms  =  allData.stream()
                .filter((Map m) -> pid.equals(m.get(parentCode)))
                .collect(Collectors.toList());
        if(ms.size()>0){
            for(Map mf: ms){
                List<Map> childMap = getzTreeData(allData,(String)mf.get(childCode));
                if(childMap.size()>0)  mf.put(childName,childMap);
            }
        }
        return ms;
    }


    public String getChildCode() {
        return childCode;
    }

    public void setChildCode(String childCode) {
        this.childCode = childCode;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

}
