package com.zjl.org.bean;

import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="sys_datasource")
@MapperXml(namespace="SysDatasource")
public class SysDatasource extends AbstractBean {

    /** 数据源id **/
    private String dataSourceId;

    /** 数据源描述 **/
    private String dataSourceDesc;

    /** 连接字符串 **/
    private String connUrl;

    /** 用户名称 **/
    private String userName;

    /** 用户密码 **/
    private String password;

    /** jdbc驱动 **/
    private String driverClass;

    /** 连接池最小数量 **/
    private Integer minPoolSize;

    /** 连接池最大数量 **/
    private Integer maxPoolSize;

    /** 连接池初始化数量 **/
    private Integer initialPoolSize;

    /** 数据库schema **/
    private String dbSchema;

    /** 数据库字符集  utf-8 gbk **/
    private String dbCharSet;

    /** 是否为jndi boolean **/
    private String jndi;

    /** jndi名称 **/
    private String jndiName;

    /** 配置获取连接等待超时的时间 **/
    private Integer maxWait;

    /** timebetweenevictionrunsmillis配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 **/
    private Integer tberm;

    /** minevictableidletimemillis配置一个连接在池中最小生存的时间，单位是毫秒 **/
    private Integer meitm;

    /** 申请连接时执行validationquery检测连接是否有效，做了这个配置会降低性能 **/
    private Integer testOnBorrow;

    /** 归还连接时执行validationquery检测连接是否有效，做了这个配置会降低性能 **/
    private Integer testOnReturn;

    /** 是否缓存preparedstatement，也就是pscache **/
    private Integer poolPreparedStatements;

    /** 要启用pscache，必须配置大于0，当大于0时， poolpreparedstatements自动触发修改为true在druid中，不会存在oracle下pscache占用内存过多的问题,比如说100 **/
    private Integer mppspcs;

    /** 配置监控统计拦截的filters **/
    private String filters;

    /** 分钟把监控数据输出到日志中 **/
    private Integer timeBetweenLogStatsMillis;

    /** 监控日志实现类 **/
    private String statLogger;

    /** 数据源编号 **/
    private String drNum;

    /** 生效状态 **/
    private String status;



    public  String getDataSourceId() {
        return dataSourceId;
    }

    public void setDataSourceId(String dataSourceId) {
        this.dataSourceId = dataSourceId;
    }


    public  String getDataSourceDesc() {
        return dataSourceDesc;
    }

    public void setDataSourceDesc(String dataSourceDesc) {
        this.dataSourceDesc = dataSourceDesc;
    }


    public  String getConnUrl() {
        return connUrl;
    }

    public void setConnUrl(String connUrl) {
        this.connUrl = connUrl;
    }


    public  String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public  String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public  String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }


    public  Integer getMinPoolSize() {
        return minPoolSize;
    }

    public void setMinPoolSize(Integer minPoolSize) {
        this.minPoolSize = minPoolSize;
    }


    public  Integer getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(Integer maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }


    public  Integer getInitialPoolSize() {
        return initialPoolSize;
    }

    public void setInitialPoolSize(Integer initialPoolSize) {
        this.initialPoolSize = initialPoolSize;
    }


    public  String getDbSchema() {
        return dbSchema;
    }

    public void setDbSchema(String dbSchema) {
        this.dbSchema = dbSchema;
    }


    public  String getDbCharSet() {
        return dbCharSet;
    }

    public void setDbCharSet(String dbCharSet) {
        this.dbCharSet = dbCharSet;
    }


    public  String getJndi() {
        return jndi;
    }

    public void setJndi(String jndi) {
        this.jndi = jndi;
    }


    public  String getJndiName() {
        return jndiName;
    }

    public void setJndiName(String jndiName) {
        this.jndiName = jndiName;
    }


    public  Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }


    public  Integer getTberm() {
        return tberm;
    }

    public void setTberm(Integer tberm) {
        this.tberm = tberm;
    }


    public  Integer getMeitm() {
        return meitm;
    }

    public void setMeitm(Integer meitm) {
        this.meitm = meitm;
    }


    public  Integer getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(Integer testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }


    public  Integer getTestOnReturn() {
        return testOnReturn;
    }

    public void setTestOnReturn(Integer testOnReturn) {
        this.testOnReturn = testOnReturn;
    }


    public  Integer getPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public void setPoolPreparedStatements(Integer poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }


    public  Integer getMppspcs() {
        return mppspcs;
    }

    public void setMppspcs(Integer mppspcs) {
        this.mppspcs = mppspcs;
    }


    public  String getFilters() {
        return filters;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }


    public  Integer getTimeBetweenLogStatsMillis() {
        return timeBetweenLogStatsMillis;
    }

    public void setTimeBetweenLogStatsMillis(Integer timeBetweenLogStatsMillis) {
        this.timeBetweenLogStatsMillis = timeBetweenLogStatsMillis;
    }


    public  String getStatLogger() {
        return statLogger;
    }

    public void setStatLogger(String statLogger) {
        this.statLogger = statLogger;
    }


    public  String getDrNum() {
        return drNum;
    }

    public void setDrNum(String drNum) {
        this.drNum = drNum;
    }


    public  String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
