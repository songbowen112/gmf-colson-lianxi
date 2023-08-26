package com.colson.common.utils;

import com.itextpdf.text.pdf.BaseFont;
import com.lowagie.text.DocumentException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * pdf 生成
 *
 * @ClassName: PdfGenerator
 * @Description:pdf 生成
 */
public class PdfDocumentGenerator {

    /**
     * 功能描述：流输出文件
     * @param template
     * @param map
     * @return
     * @author 李斌
     * @since 2014年12月18日
     */
    public ByteArrayInputStream generateStream(String template, String fontPath, Map<String, Object> map) {
        try {
            HtmlGenerator htmlGenerator = new HtmlGenerator();
            String htmlContent = htmlGenerator.generate(template, map);
            return generateStream(htmlContent, fontPath);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 功能描述：流输出文件
     * @param template
     * @param map
     * @return
     * @author 李斌
     * @since 2014年12月18日
     */
    public ByteArrayInputStream generateStream(String template, Map<String, Object> map) {
        return generateStream(template, null, map);
    }

    public ByteArrayInputStream generateStream(String htmlContent, String fontPath) {
        htmlContent = htmlContent.replace("<br>", "<br/>");
        htmlContent = htmlContent.replaceAll("(<img.*?)(>|/>)", "$1/>");
        /**
         * addby chenqiuxia 20180522 修改 "<" 或 ">" 导致的问题
         */
        htmlContent = htmlContent.replace("&gt;", "@@@@1");
        htmlContent = htmlContent.replace("&lt;", "@@@@2");
        htmlContent = HtmlUtils.htmlUnescape(htmlContent);
        htmlContent = htmlContent.replace("&","&amp;"); // addby chenqiuxia 20180525 解决“20世纪60年代的P&G公司”中由于误读“&”导致的引用问题
        htmlContent = htmlContent.replace("@@@@1", "&gt;");
        htmlContent = htmlContent.replace("@@@@2", "&lt;");
        ITextRenderer iTextRenderer = new ITextRenderer();
        ByteArrayOutputStream ostream = new ByteArrayOutputStream();
        ByteArrayInputStream stream1 = null;
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(htmlContent
                    .getBytes("UTF-8")));
            ITextFontResolver fontResolver = iTextRenderer.getFontResolver();
            if (StringUtils.isEmpty(fontPath)) {
                fontPath = Thread.currentThread().getContextClassLoader()
                        .getResource(".." + File.separator + "..").getPath()
                        + "pdfCfg"
                        + File.separator
                        + "fonts"
                        + ""
                        + File.separator
                        + "simsun.ttc";
            }
//            String fontPath = Thread.currentThread().getContextClassLoader()
//                    .getResource(".." + File.separator + "..").getPath()
//                    + "pdfCfg"
//                    + File.separator
//                    + "fonts"
//                    + File.separator
//                    + "msyh.ttf";
            fontResolver.addFont(fontPath, BaseFont.IDENTITY_H,
                    BaseFont.EMBEDDED);
            iTextRenderer.setDocument(doc, null);
            iTextRenderer.layout();
            iTextRenderer.createPDF(ostream);
            stream1 = new ByteArrayInputStream(ostream.toByteArray());

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } finally {
            try {
                ostream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stream1;
    }

}