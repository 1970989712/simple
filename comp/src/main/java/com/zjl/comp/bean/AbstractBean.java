package com.zjl.comp.bean;

import java.util.HashMap;
import java.util.Map;


public abstract class AbstractBean implements IBomfBean {

    private static final long serialVersionUID = 5494093173963838179L;

    /**
     * 再对象中,不返回前端
     */
    protected Map<String, Object> extData = null;

    public void addExtAttribute(String fieldName, Object obj) {
        if (this.extData == null) {
            this.extData = new HashMap();
        }

        this.extData.put(fieldName, obj);
    }

    public Map<String, Object> getExtData() {
        return this.extData;
    }

}
