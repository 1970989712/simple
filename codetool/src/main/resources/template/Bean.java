package #(pageName).bean;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.zjl.comp.anno.MapperXml;
import com.zjl.comp.anno.Table;
import com.zjl.comp.bean.AbstractBean;
import java.util.*;

@Table(name="#(tableName)")
@MapperXml(namespace="#(beanName)")
@ContentRowHeight(30)//内容高度
@HeadRowHeight(30)//头部高度
@ColumnWidth(25)//列宽
public class #(beanName) extends AbstractBean {

#for(x : tablist)
    /** #(x.columnComment) **/
    private #(x.dataTypejava) #(x.beanfildname);

#end

#for(x : tablist)

    public  #(x.dataTypejava) get#(firstCharToUpperCase(x.beanfildname))() {
        return #(x.beanfildname);
    }

    public void set#(firstCharToUpperCase(x.beanfildname))(#(x.dataTypejava) #(x.beanfildname)) {
        this.#(x.beanfildname) = #(x.beanfildname);
    }

#end
}

