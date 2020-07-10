package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_dictionary")
@MapperXml(namespace="SysDictionary")
public class SysDictionary extends AbstractBean {

    /** 字典主键 **/
    private String dictionaryId;

    /** 类型ID **/
    private String codeItemId;

    /** 字典ID **/
    private String codeId;

    /** 名称 **/
    private String codeName;

    /** 排序 **/
    private Integer sortNo;

    /** 是否维护 **/
    private String isUpdate;

    /** 创建时间 **/
    private Date createTime;

    /** 创建人 **/
    private String createUser;

    /** 更新时间 **/
    private Date updateTime;

    /** 修改人 **/
    private String updateUser;



    public  String getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(String dictionaryId) {
        this.dictionaryId = dictionaryId;
    }


    public  String getCodeItemId() {
        return codeItemId;
    }

    public void setCodeItemId(String codeItemId) {
        this.codeItemId = codeItemId;
    }


    public  String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }


    public  String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }


    public  Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }


    public  String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate;
    }


    public  Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    public  String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }


    public  Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


    public  String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

}
