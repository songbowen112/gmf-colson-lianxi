package com.colson.common.docx4j;

import org.docx4j.XmlUtils;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/23 19:08
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class XmlChapterHandler extends AbstractCrossDictHandler {

    private List<CrossDictChapterKnowledge> chapters;

    public XmlChapterHandler(WordMLPackageWare ware) {
        super(ware);
        this.chapters = new ArrayList<>();
    }

    /**
     * 加入章节
     *
     * @param chapter
     */
    public XmlChapterHandler addChapter(CrossDictChapterKnowledge chapter) {
        this.chapters.add(chapter);
        return this;
    }

    @Override
    public void handle() throws Exception {
        for (CrossDictChapterKnowledge chapter : chapters) {
            printChapter(chapter);
        }
        return;
    }

    /**
     * 输出章节内容
     *
     * @param chapter
     * @throws JAXBException
     */
    private void printChapter(CrossDictChapterKnowledge chapter) throws Exception {
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlChapterTitle(chapter.getChapterLevel(), chapter.getChapterName())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlParagraphBeforeTable(chapter.getChapterLevel())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlTableDataList(chapter.getTableDataList())));
        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlChapterDescription("")));
        List<String> chapterDescriptions = chapter.getChapterDescriptions();
        if (null != chapterDescriptions && !chapterDescriptions.isEmpty()) {
            for (String chapterDescription : chapterDescriptions) {
                if (chapterDescription.startsWith("<img")) {
                    Pattern compile = Pattern.compile("<img src=\\\"(.*?)\\\" .+>");
                    Matcher matcher = compile.matcher(chapterDescription);
                    if (matcher.find()) {
                        // 下载图片, 并插入到文档中
                        WebImageHandlerUtil.handle(this.ware, matcher.group(1));
//                        this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlChapterDescription(matcher.group(1))));
                    }
                } else {
                    chapterDescription = Docx4jUtils.paragraphSpecialFilter(chapterDescription);
                    this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlChapterDescription(chapterDescription)));
                }
            }
            this.ware.getWordMLPackage().getMainDocumentPart().getContent().add(XmlUtils.unmarshalString(xmlChapterDescription("")));
        }

        List<CrossDictChapterKnowledge> subChapters = chapter.getSubChapters();
        if (null == subChapters || subChapters.isEmpty()) {
            return;
        } else {
            for (CrossDictChapterKnowledge subChapter : subChapters) {
                printChapter(subChapter);
            }
        }
    }

    /**
     * 如果是低于一级地址的内容, 需要在表的前面输出考察分值和次数等字样
     *
     * @param chapterLevel
     * @return
     */
    private String xmlParagraphBeforeTable(int chapterLevel) {
        if (chapterLevel == 1) {
            return Docx4jUtils.xmlBlankLine();
        }
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00025353\" w:rsidRPr=\"00025353\"\n" +
                "                         w:rsidRDefault=\"00025353\" w:rsidP=\"00025353\">\n" +
                "                        <w:pPr>\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                <w:b/>\n" +
                "                                <w:sz w:val=\"24\"/>\n" +
                "                                <w:szCs w:val=\"24\"/>\n" +
                "                            </w:rPr>\n" +
                "                        </w:pPr>\n" +
                "                        <w:r w:rsidRPr=\"00025353\">\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"\n" +
                "                                          w:hint=\"eastAsia\"/>\n" +
                "                                <w:b/>\n" +
                "                                <w:sz w:val=\"24\"/>\n" +
                "                                <w:szCs w:val=\"24\"/>\n" +
                "                            </w:rPr>\n" +
                "                            <w:t>考察分值和次数</w:t>\n" +
                "                        </w:r>\n" +
                "                    </w:p>";
    }

    /**
     * 章节描述处理
     *
     * @param chapterDescription
     * @return
     */
    private String xmlChapterDescription(String chapterDescription) {
        return "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00452203\" w:rsidRPr=\"007F2770\"\n" +
                "                         w:rsidRDefault=\"001C5C77\" w:rsidP=\"007F2770\">\n" +
                "                        <w:pPr>\n" +
                "                            <w:widowControl/>\n" +
                "                            <w:spacing w:line=\"360\" w:lineRule=\"exact\"/>\n" +
                "                            <w:ind w:firstLine=\"450\"/>\n" +
                "                            <w:jc w:val=\"left\"/>\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\"/>\n" +
                "                                <w:kern w:val=\"0\"/>\n" +
                "                                <w:sz w:val=\"24\"/>\n" +
                "                                <w:szCs w:val=\"24\"/>\n" +
                "                            </w:rPr>\n" +
                "                        </w:pPr>\n" +
                "                        <w:r w:rsidRPr=\"007F2770\">\n" +
                "                            <w:rPr>\n" +
                "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                <w:kern w:val=\"0\"/>\n" +
                "                                <w:sz w:val=\"24\"/>\n" +
                "                                <w:szCs w:val=\"24\"/>\n" +
                "                                <w:shd w:val=\"clear\" w:color=\"auto\" w:fill=\"FFFFFF\"/>\n" +
                "                            </w:rPr>\n" +
                "                            <w:t>" + chapterDescription + "</w:t>\n" +
                "                        </w:r>\n" +
                "                    </w:p>";
    }

    /**
     * 表格数据处理
     *
     * @param tableDataList
     * @return
     */
    private String xmlTableDataList(CrossDictTableData tableDataList) {
        return "<w:tbl xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" >\n" +
                "                        <w:tblPr>\n" +
                "                            <w:tblW w:w=\"0\" w:type=\"auto\"/>\n" +
                "                            <w:jc w:val=\"center\"/>\n" +
                "                            <w:tblBorders>\n" +
                "                                <w:top w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
                "                                <w:left w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
                "                                <w:bottom w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
                "                                <w:right w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
                "                                <w:insideH w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
                "                                <w:insideV w:val=\"single\" w:sz=\"4\" w:space=\"0\" w:color=\"auto\"/>\n" +
                "                            </w:tblBorders>\n" +
                "                            <w:tblLayout w:type=\"fixed\"/>\n" +
                "                            <w:tblLook w:val=\"0000\" w:firstRow=\"0\" w:lastRow=\"0\" w:firstColumn=\"0\" w:lastColumn=\"0\"\n" +
                "                                       w:noHBand=\"0\" w:noVBand=\"0\"/>\n" +
                "                        </w:tblPr>\n" +
                "                        <w:tblGrid>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                            <w:gridCol w:w=\"1060\"/>\n" +
                "                        </w:tblGrid>\n" +
                "                        <w:tr w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                              w:rsidTr=\"00186180\">\n" +
                "                            <w:trPr>\n" +
                "                                <w:trHeight w:val=\"332\"/>\n" +
                "                                <w:jc w:val=\"center\"/>\n" +
                "                            </w:trPr>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                       <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>知识点层级</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>大纲要求</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>常考题型</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>考察次数</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>考察次数占比</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>考察分值</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>考察分值占比</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                        </w:tr>\n" +
                "                        <w:tr w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                              w:rsidTr=\"00BA0FA8\">\n" +
                "                            <w:trPr>\n" +
                "                                <w:trHeight w:val=\"260\"/>\n" +
                "                                <w:jc w:val=\"center\"/>\n" +
                "                            </w:trPr>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge w:val=\"restart\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getLevel() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge w:val=\"restart\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"宋体\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getRequire() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(0).getInvestigateTimes() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(0).getInvestigateTimesProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(0).getInvestigateScore() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(0).getInvestigateScoreProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                        </w:tr>\n" +
                "                        <w:tr w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                              w:rsidTr=\"00BA0FA8\">\n" +
                "                            <w:trPr>\n" +
                "                                <w:trHeight w:val=\"260\"/>\n" +
                "                                <w:jc w:val=\"center\"/>\n" +
                "                            </w:trPr>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(1).getTypeName() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(1).getInvestigateTimes() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(1).getInvestigateTimesProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(1).getInvestigateScore() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(1).getInvestigateScoreProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                        </w:tr>\n" +
                "                        <w:tr w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\" \n" +
                "                              w:rsidTr=\"00BA0FA8\">\n" +
                "                            <w:trPr>\n" +
                "                                <w:trHeight w:val=\"260\"/>\n" +
                "                                <w:jc w:val=\"center\"/>\n" +
                "                            </w:trPr>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(2).getTypeName() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(2).getInvestigateTimes() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(2).getInvestigateTimesProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(2).getInvestigateScore() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(2).getInvestigateScoreProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                        </w:tr>\n" +
                "                        <w:tr w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\" \n" +
                "                              w:rsidTr=\"00BA0FA8\">\n" +
                "                            <w:trPr>\n" +
                "                                <w:trHeight w:val=\"260\"/>\n" +
                "                                <w:jc w:val=\"center\"/>\n" +
                "                            </w:trPr>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vMerge/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"MS Mincho\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(3).getTypeName() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(3).getInvestigateTimes() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(3).getInvestigateTimesProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(3).getInvestigateScore() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                            <w:tc>\n" +
                "                                <w:tcPr>\n" +
                "                                    <w:tcW w:w=\"1060\" w:type=\"dxa\"/>\n" +
                "                                    <w:vAlign w:val=\"center\"/>\n" +
                "                                </w:tcPr>\n" +
                "                                <w:p w:rsidR=\"00E442BD\" w:rsidRPr=\"00E442BD\"\n" +
                "                                     w:rsidRDefault=\"00E442BD\" w:rsidP=\"00BA0FA8\">\n" +
                "                                    <w:pPr>\n" +
                "                                        <w:autoSpaceDE w:val=\"0\"/>\n" +
                "                                        <w:autoSpaceDN w:val=\"0\"/>\n" +
                "                                        <w:adjustRightInd w:val=\"0\"/>\n" +
                "                                        <w:jc w:val=\"center\"/>\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                    </w:pPr>\n" +
                "                                    <w:r w:rsidRPr=\"00E442BD\">\n" +
                "                                        <w:rPr>\n" +
                "                                            <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:cs=\"Arial\"/>\n" +
                "                                            <w:color w:val=\"000000\"/>\n" +
                "                                            <w:sz w:val=\"14\"/>\n" +
                "                                            <w:szCs w:val=\"14\"/>\n" +
                "                                        </w:rPr>\n" +
                "                                        <w:t>" + tableDataList.getPerRowDataList().get(3).getInvestigateScoreProportion() + "</w:t>\n" +
                "                                    </w:r>\n" +
                "                                </w:p>\n" +
                "                            </w:tc>\n" +
                "                        </w:tr>\n" +
                "                    </w:tbl>";
    }

    /**
     * 章节标题处理
     *
     * @param level       章节级别: 提供4种级别
     * @param chapterName 章节标题名称:
     * @return
     */
    private String xmlChapterTitle(int level, String chapterName) {
        // &符号会引发xml解析异常, 先这么解决吧
        chapterName = chapterName.replaceAll("&", "and");
        if (chapterName.contains("<")) {
            chapterName = chapterName.replaceAll("<", "《");
        }
        if (chapterName.contains(">")) {
            chapterName = chapterName.replaceAll(">", "》");
        }
        String xml;
        switch (level) {
            case 1:
                xml = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00452203\" w:rsidRPr=\"00E442BD\"\n" +
                        "                         w:rsidRDefault=\"00E442BD\">\n" +
                        "                        <w:pPr>\n" +
                        "                            <w:pStyle w:val=\"1\"/>\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:hAnsi=\"微软雅黑\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                        </w:pPr>\n" +
                        "                        <w:r w:rsidRPr=\"00E442BD\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:lang w:eastAsia=\"zh-CN\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:lastRenderedPageBreak/>\n" +
                        "                            <w:t xml:space=\"preserve\"></w:t>\n" +
                        "                        </w:r>\n" +
                        "                        <w:proofErr w:type=\"spellStart\"/>\n" +
                        "                        <w:r w:rsidRPr=\"00E442BD\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:t>" + chapterName + "</w:t>\n" +
                        "                        </w:r>\n" +
                        "                        <w:proofErr w:type=\"spellEnd\"/>\n" +
                        "                    </w:p>";
                break;
            case 2:
                xml = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\"  w:rsidR=\"00884E5A\" w:rsidRPr=\"00884E5A\"\n" +
                        "                         w:rsidRDefault=\"00E442BD\" w:rsidP=\"00884E5A\">\n" +
                        "                        <w:pPr>\n" +
                        "                            <w:pStyle w:val=\"2\"/>\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:sz w:val=\"28\"/>\n" +
                        "                                <w:szCs w:val=\"28\"/>\n" +
                        "                                <w:lang w:eastAsia=\"zh-CN\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                        </w:pPr>\n" +
                        "                        <w:bookmarkStart w:id=\"2\" w:name=\"_Toc507406283\"/>\n" +
                        "                        <w:bookmarkEnd w:id=\"1\"/>\n" +
                        "                        <w:proofErr w:type=\"spellStart\"/>\n" +
                        "                        <w:proofErr w:type=\"spellEnd\"/>\n" +
                        "                        <w:r w:rsidRPr=\"00884E5A\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:sz w:val=\"28\"/>\n" +
                        "                                <w:szCs w:val=\"28\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:t xml:space=\"preserve\"> </w:t>\n" +
                        "                        </w:r>\n" +
                        "                        <w:proofErr w:type=\"spellStart\"/>\n" +
                        "                        <w:bookmarkStart w:id=\"3\" w:name=\"_GoBack\"/>\n" +
                        "                        <w:bookmarkEnd w:id=\"3\"/>\n" +
                        "                        <w:r w:rsidRPr=\"00884E5A\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:sz w:val=\"28\"/>\n" +
                        "                                <w:szCs w:val=\"28\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:t>" + chapterName + "</w:t>\n" +
                        "                        </w:r>\n" +
                        "                        <w:bookmarkEnd w:id=\"2\"/>\n" +
                        "                        <w:proofErr w:type=\"spellEnd\"/>\n" +
                        "                    </w:p>";
                break;
            case 3:
                xml = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00381E13\" w:rsidRDefault=\"00381E13\"\n" +
                        "                         w:rsidP=\"00C37AB6\">\n" +
                        "                        <w:pPr>\n" +
                        "                            <w:pStyle w:val=\"4\"/>\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\"/>\n" +
                        "                                <w:b w:val=\"0\"/>\n" +
                        "                                <w:bCs w:val=\"0\"/>\n" +
                        "                                <w:sz w:val=\"24\"/>\n" +
                        "                                <w:szCs w:val=\"24\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                        </w:pPr>\n" +
                        "                        <w:r w:rsidRPr=\"00E442BD\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:sz w:val=\"24\"/>\n" +
                        "                                <w:szCs w:val=\"24\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:t xml:space=\"preserve\"> </w:t>\n" +
                        "                        </w:r>\n" +
                        "                        <w:r w:rsidRPr=\"00381E13\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:sz w:val=\"24\"/>\n" +
                        "                                <w:szCs w:val=\"24\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:t>" + chapterName + "</w:t>\n" +
                        "                        </w:r>\n" +
                        "                    </w:p>";
                break;
            case 4:
            default:
                xml = "<w:p xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:rsidR=\"00381E13\" w:rsidRDefault=\"00381E13\"\n" +
                        "                         w:rsidP=\"00166706\">\n" +
                        "                        <w:pPr>\n" +
                        "                            <w:pStyle w:val=\"5\"/>\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\"/>\n" +
                        "                                <w:sz w:val=\"24\"/>\n" +
                        "                                <w:szCs w:val=\"24\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                        </w:pPr>\n" +
                        "                        <w:bookmarkStart w:id=\"5\" w:name=\"_Toc507430863\"/>\n" +
                        "                        <w:r w:rsidRPr=\"00381E13\">\n" +
                        "                            <w:rPr>\n" +
                        "                                <w:rFonts w:ascii=\"微软雅黑\" w:eastAsia=\"微软雅黑\" w:hAnsi=\"微软雅黑\" w:hint=\"eastAsia\"/>\n" +
                        "                                <w:sz w:val=\"24\"/>\n" +
                        "                                <w:szCs w:val=\"24\"/>\n" +
                        "                            </w:rPr>\n" +
                        "                            <w:t>" + chapterName + "</w:t>\n" +
                        "                        </w:r>\n" +
                        "                        <w:bookmarkEnd w:id=\"5\"/>\n" +
                        "                    </w:p>";
                break;
        }
        return xml;
    }
}
