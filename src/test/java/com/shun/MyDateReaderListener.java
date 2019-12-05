package com.shun;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyDateReaderListener extends AnalysisEventListener<MyData> {

    private List list = new ArrayList();
    @Override
    public void invoke(MyData myData, AnalysisContext analysisContext) {
        list.add(myData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        list.forEach(l-> System.out.println(l));
    }
}
