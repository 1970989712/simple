package com.zjl.comp.bean;


import java.io.Serializable;
import java.util.Map;

public interface IBomfBean extends Serializable {

    void addExtAttribute(String attribute, Object obj);

    Map<String, Object> getExtData();

}
