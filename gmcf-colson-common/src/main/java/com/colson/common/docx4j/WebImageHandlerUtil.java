package com.sunlands.analyze.docx4j;

import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/24 10:30
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class WebImageHandlerUtil {

    private static final Logger logger = LoggerFactory.getLogger(WebImageHandlerUtil.class);

    public static void handle(WordMLPackageWare ware, String url) throws Exception {
        try {
            byte[] imageBytes = getImageBytes(url);
            if (null != imageBytes && imageBytes.length > 0) {
                ware.getWordMLPackage().getMainDocumentPart().getContent().add((newImage(ware.getWordMLPackage(), imageBytes, null, null, 10, 11)));
            }
        } catch (Exception e) {
            logger.error("insert image error, image url: {}", url);
        }
    }

    private static byte[] getImageBytes(String url) throws IOException {
        byte[] bytesFromUrl = null;
        try {
            bytesFromUrl = OkHttpUtils.getBytesFromUrl(url);
        } catch (IOException e) {
            // 如果发生异常就重试3次, 如果3次后仍然失败就放弃
            for (int i = 1; i <= 3; i++) {
                logger.error("下载图片失败, 重试第{}次, url: {}, message: {}", i, url, e.getMessage());
                try {
                    bytesFromUrl = OkHttpUtils.getBytesFromUrl(url);
                } catch (IOException e1) {

                }
                if (null != bytesFromUrl && bytesFromUrl.length > 0) {
                    break;
                }
            }
        }
        return bytesFromUrl;
    }

    /**
     * Create image, specifying width in twips
     */
    private static org.docx4j.wml.P newImage( WordprocessingMLPackage wordMLPackage,
                                             byte[] bytes,
                                             String filenameHint, String altText,
                                             int id1, int id2, long cx) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline( filenameHint, altText,
                id1, id2, cx, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        org.docx4j.wml.P  p = factory.createP();
        org.docx4j.wml.R  run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }

    /**
     * Create image, without specifying width
     */
    private static org.docx4j.wml.P newImage( WordprocessingMLPackage wordMLPackage,
                                             byte[] bytes,
                                             String filenameHint, String altText,
                                             int id1, int id2) throws Exception {

        BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

        Inline inline = imagePart.createImageInline( filenameHint, altText,
                id1, id2, false);

        // Now add the inline in w:p/w:r/w:drawing
        org.docx4j.wml.ObjectFactory factory = Context.getWmlObjectFactory();
        org.docx4j.wml.P  p = factory.createP();
        org.docx4j.wml.R  run = factory.createR();
        p.getContent().add(run);
        org.docx4j.wml.Drawing drawing = factory.createDrawing();
        run.getContent().add(drawing);
        drawing.getAnchorOrInline().add(inline);

        return p;

    }
}
