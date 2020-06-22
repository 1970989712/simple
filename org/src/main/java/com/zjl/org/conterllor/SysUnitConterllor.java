package com.zjl.org.conterllor;

import com.zjl.comp.anno.ZjlJson;
import com.zjl.comp.conterllor.BaseConterllor;
import com.zjl.comp.util.SpringUtil;
import com.zjl.comp.util.TreeJson;
import com.zjl.org.bean.SysUnit;
import com.zjl.org.dao.SysUnitDao;
import com.zjl.org.service.SysUnitService;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/org/SysUnitService")
public class SysUnitConterllor extends BaseConterllor<SysUnit> {


    @ZjlJson
    @RequestMapping("/synctree")
    public List selectAll(String parentId) throws Exception {
        if(null==parentId||""==parentId) parentId = "0";
        List<SysUnit> allData = SpringUtil.getBean(SysUnitService.class).getAllData();
        TreeJson tree = new TreeJson();
        tree.setChildCode("unitId");
        List<Map> units = tree.getTreeDataByBean(allData,parentId);
        return units;
    }

}