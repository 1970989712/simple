package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_dictionary_type")
@MapperXml(namespace="SysDictionaryType")
public class SysDictionaryType extends AbstractBean {

    /** 字典类别标识 **/
    private String codeItemId;

    /** 字典类别名称 **/
    private String codeItemName;

    /** 数据字典类型父id **/
    private String parentId;

    /** 类型排序 **/
    private Integer sortNo;

    /** 能否被更新 **/
    private Boolean isUpdate;

    /** 创建人 **/
    private String createUser;

    /** 创建时间 **/
    private Date createTime;

    /** 修改人 **/
    private String updateUser;

    /** 修改时间 **/
    private Date updateTime;



    public  String getCodeItemId() {
        return codeItemId;
    }

    public void setCodeItemId(String codeItemId) {
        this.codeItemId = codeItemId;
    }


    public  String getCodeItemName() {
        return codeItemName;
    }

    public void setCodeItemName(String codeItemName) {
        this.codeItemName = codeItemName;
    }


    public  String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }


    public  Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    public  Boolean getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Boolean isUpdate) {
        this.isUpdate = isUpdate;
    }


    public  String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public  String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }


    public  Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
