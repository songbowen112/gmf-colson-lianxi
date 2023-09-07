package com.sunlands.analyze.docx4j;

import org.docx4j.XmlUtils;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 17:17
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class XmlHomePageHandler extends AbstractCrossDictHandler {

    private CrossDictHomePage homePage;

    public XmlHomePageHandler(CrossDictHomePage homePage, WordMLPackageWare ware) {
        super(ware);
        this.homePage = homePage;
    }

    @Override
    public void handle() throws Exception {
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(0, XmlUtils.unmarshalString(xmlSubject(this.homePage.getSubject())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(1, XmlUtils.unmarshalString(xmlAdjustProvince(this.homePage.getAdjustProvince())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(2, XmlUtils.unmarshalString(xmlExamProvince(this.homePage.getExamProvince())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(3, XmlUtils.unmarshalString(xmlRealCoverDate(this.homePage.getRealCoverDate())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(13, XmlUtils.unmarshalString(xmlMainSubject(this.homePage.getSubject())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(15, XmlUtils.unmarshalString(xmlTitle("通关宝典")));
    }

    private String xmlSubject(String subject) {
        String xml = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00452203\" w:rsidRDefault=\"0052265A\">\n" +
                "            <w:pPr>\n" +
                "                <w:spacing w:line=\"360\" w:lineRule=\"exact\"/>\n" +
                "                <w:jc w:val=\"left\"/>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "            </w:pPr>\n" +
                "            <w:r>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "                <w:t>科目：" + subject + "</w:t>\n" +
                "            </w:r>\n" +
                "        </w:p>";
        return xml;
    }

    private String xmlAdjustProvince(String adjustProvince) {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00452203\" w:rsidRDefault=\"0052265A\">\n" +
                "            <w:pPr>\n" +
                "                <w:spacing w:line=\"360\" w:lineRule=\"exact\"/>\n" +
                "                <w:jc w:val=\"left\"/>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "            </w:pPr>\n" +
                "            <w:r>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "                <w:t>适用省份：" + adjustProvince + "</w:t>\n" +
                "            </w:r>\n" +
                "        </w:p>";
    }

    private String xmlExamProvince(String examProvince) {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"0052265A\" w:rsidRDefault=\"0052265A\">\n" +
                "            <w:pPr>\n" +
                "                <w:spacing w:line=\"360\" w:lineRule=\"exact\"/>\n" +
                "                <w:jc w:val=\"left\"/>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "            </w:pPr>\n" +
                "            <w:r>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "                <w:t>考试省份：" + examProvince + "</w:t>\n" +
                "            </w:r>\n" +
                "        </w:p>";
    }

    private String xmlRealCoverDate(String realCoverDate) {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"0052265A\" w:rsidRDefault=\"0052265A\">\n" +
                "            <w:pPr>\n" +
                "                <w:spacing w:line=\"360\" w:lineRule=\"exact\"/>\n" +
                "                <w:jc w:val=\"left\"/>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "            </w:pPr>\n" +
                "            <w:r>\n" +
                "                <w:rPr>\n" +
                "                    <w:rFonts w:ascii=\"楷体\" w:eastAsia=\"楷体\" w:hAnsi=\"楷体\" w:cs=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                "                    <w:bCs/>\n" +
                "                    <w:sz w:val=\"28\"/>\n" +
                "                    <w:szCs w:val=\"28\"/>\n" +
                "                </w:rPr>\n" +
                "                <w:t>真题覆盖考期：" + realCoverDate + "</w:t>\n" +
                "            </w:r>\n" +
                "        </w:p>";
    }

    public String xmlMainSubject(String mainSubject) {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00452203\" w:rsidRDefault=\"0052265A\" w:rsidP=\"00842F5F\">\n" +
                "                        <w:pPr>\n" +
                "                            <w:spacing w:line=\"500\" w:lineRule=\"exact\"/>\n" +
                "                            <w:jc w:val=\"center\"/>\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\"/>\n" +
                "                                <w:b/>\n" +
                "                                <w:bCs/>\n" +
                "                                <w:sz w:val=\"48\"/>\n" +
                "                                <w:szCs w:val=\"48\"/>\n" +
                "                            </w:rPr>\n" +
                "                        </w:pPr>\n" +
                "                        <w:bookmarkStart w:id=\"0\" w:name=\"_GoBack\"/>\n" +
                "                        <w:bookmarkEnd w:id=\"0\"/>\n" +
                "                        <w:r>\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                "                                <w:b/>\n" +
                "                                <w:bCs/>\n" +
                "                                <w:sz w:val=\"48\"/>\n" +
                "                                <w:szCs w:val=\"48\"/>\n" +
                "                            </w:rPr>\n" +
                "                            <w:t>" + mainSubject + "</w:t>\n" +
                "                        </w:r>\n" +
                "                    </w:p>";
    }

    private String xmlTitle(String title) {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00765A9A\" w:rsidRDefault=\"00765A9A\"\n" +
                "                         w:rsidP=\"00765A9A\">\n" +
                "                        <w:pPr>\n" +
                "                            <w:spacing w:line=\"500\" w:lineRule=\"exact\"/>\n" +
                "                            <w:jc w:val=\"center\"/>\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\"/>\n" +
                "                                <w:b/>\n" +
                "                                <w:bCs/>\n" +
                "                                <w:sz w:val=\"48\"/>\n" +
                "                                <w:szCs w:val=\"48\"/>\n" +
                "                            </w:rPr>\n" +
                "                        </w:pPr>\n" +
                "                        <w:r>\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                "                                <w:b/>\n" +
                "                                <w:bCs/>\n" +
                "                                <w:sz w:val=\"48\"/>\n" +
                "                                <w:szCs w:val=\"48\"/>\n" +
                "                            </w:rPr>\n" +
                "                            <w:t>"+title+"</w:t>\n" +
                "                        </w:r>\n" +
                "                    </w:p>";
    }
}
