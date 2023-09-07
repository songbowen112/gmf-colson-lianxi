package com.sunlands.analyze.docx4j;

import java.io.*;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 17:24
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class SimpleCrossDict extends AbstractCrossDict {

    public SimpleCrossDict(String fileLocation) throws Exception {
        super(fileLocation);
    }

    public SimpleCrossDict(InputStream inputStream) throws Exception {
        super(inputStream);
    }

    private SimpleCrossDict() {
        super();
    }

    @Override
    public SimpleCrossDict clone() {
        SimpleCrossDict newObj = new SimpleCrossDict();
        CrossDictBuilder clone = newObj.clone(this);
        return (SimpleCrossDict) clone;
    }
}
