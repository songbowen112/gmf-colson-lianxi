package com.colson.common.docx4j;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 19:09
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public abstract class AbstractCrossDictHandler implements CrossDictHandler, WordMLPackageWare{

    protected WordMLPackageWare ware;

    public AbstractCrossDictHandler(WordMLPackageWare ware) {
        this.ware = ware;
    }

    @Override
    public WordprocessingMLPackage getWordMLPackage() {
        if (null != ware) {
            return ware.getWordMLPackage();
        }
        return null;
    }
}
