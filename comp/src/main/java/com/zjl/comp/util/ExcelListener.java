package com.zjl.comp.util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.zjl.comp.dao.util.BeanDaoHelper;
import com.zjl.comp.exception.MyException;
import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> extends AnalysisEventListener<T> {

    private List<T> dataList = new ArrayList<>();

    @Override
    public void invoke(T result, AnalysisContext context) {
        if (context.readRowHolder().getRowIndex() != 0) dataList.add(result);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        try {
            SpringUtil.getBean(BeanDaoHelper.class).getBeanDao(analysisContext.readWorkbookHolder().getClazz()).customizeAllBatch("insert",dataList);
           // updservice.batchadd(dataList);  //数据导入 待提供一个通用的导入
        } catch (Exception e) {
            throw new MyException(e+"--导入异常！");
        }
    }

}