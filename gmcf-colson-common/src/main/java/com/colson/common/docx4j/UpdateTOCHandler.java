package com.sunlands.analyze.docx4j;

import org.docx4j.toc.TocGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 17:03
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class UpdateTOCHandler extends AbstractCrossDictHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public UpdateTOCHandler(WordMLPackageWare ware) {
        super(ware);
    }

    @Override
    public void handle() throws Exception {
        logger.info("update word toc");
        if (null != ware.getWordMLPackage()) {
            TocGenerator tocGenerator = new TocGenerator(ware.getWordMLPackage());
            tocGenerator.updateToc(true);
            logger.info("update word success");
        } else {
            logger.error("wordMLPackage is null");
        }
    }
}
