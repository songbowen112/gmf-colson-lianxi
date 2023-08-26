package com.colson.common.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author maqi
 * @date 2020/10/29 17:10
 */
public class PdfUtils {

    // 定义全局的字体静态变量
    private static Font waterMarkFont;
    private static Font headFont;
    private static Font footFont;
    private static Integer headX;
    private static Integer headY;
    private static Integer footX;
    private static Integer footY;

    //pdf页眉页脚图片地址
    private static String logoUrl = PathUtil.getCurrentPath() + "/pdf/pic/logo.png";
    private static String qrCodeUrl = "https://sfs-public.shangdejigou.cn/user_center/pdfImage/qrCode.png";
    // 静态代码块
    static {
        try {
            // 不同字体（这里定义为同一种字体：包含不同字号、不同style）
            BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            waterMarkFont = new Font(bfChinese, 16, Font.BOLD);
            headFont = new Font(bfChinese, 18, Font.NORMAL);
            footFont = new Font(bfChinese, 18, Font.NORMAL);
            headX = 36;
            headY = 1260;
            footX = 780;
            footY = 0;


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWaterMarkByFile(InputStream inputStream, String FilePath, String text, String basePath) {
        try {
            // 原PDF文件
            PdfReader reader = new PdfReader(inputStream);
            // 输出的PDF文件内容
            PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(FilePath));

            // 字体 来源于 itext-asian JAR包
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);

            //pdf页眉页脚图片地址
            String logoUrl = basePath + "/pdf/pic/logo.png";
            String qrCodeUrl = basePath + "/pdf/pic/QRcode.png";
            Image logoImage = Image.getInstance(logoUrl);
            Image qrCodeImage = Image.getInstance(qrCodeUrl);

            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            // 设置透明度
            gs.setFillOpacity(0.04f);
            gs.setStrokeOpacity(0.04f);

            PdfGState gs2 = new PdfGState();
            // 设置透明度
            gs2.setFillOpacity(1f);
            gs2.setStrokeOpacity(1f);

//            JLabel label = new JLabel();
//            FontMetrics metrics;
//            label.setText(text);
//            metrics = label.getFontMetrics(label.getFont());
//            int textH = metrics.getHeight();
//            int textW = metrics.stringWidth(label.getText());

            int totalPage = reader.getNumberOfPages() + 1;
            for (int i = 1; i < totalPage; i++) {
                if (i == 1) {
                    continue;
                }
                pageRect = reader.getPageSizeWithRotation(i);
                //内容上层
                PdfContentByte overContent = stamper.getOverContent(i);
                //内容下层
                PdfContentByte underContent = stamper.getUnderContent(i);

                overContent.beginText();
                overContent.saveState();
                // 字体添加透明度
                overContent.setGState(gs);
                overContent.setFontAndSize(baseFont, 66);
                overContent.showTextAligned(Element.ALIGN_CENTER, text, pageRect.getWidth() / 2, pageRect.getHeight() / 2, 30);

                underContent.beginText();
                underContent.setFontAndSize(baseFont, 18);
                underContent.setGState(gs2);
                underContent.saveState();
                // 添加页脚，左侧文字，右侧页码
                ColumnText.showTextAligned(underContent, Element.ALIGN_CENTER,
                        new Phrase(String.format("关注公众号【牛哥自学备考】         第 %d 页/共 %d 页          扫码关注领取免费资料", i - 1, totalPage - 2), footFont), pageRect.getWidth() / 2, 40, 0);
                if (qrCodeImage != null){
                    qrCodeImage.setAbsolutePosition(footX, footY);
                    underContent.addImage(qrCodeImage);
                    underContent.saveState();
                }

                // 添加页眉 (文字页眉，居中)
                ColumnText.showTextAligned(underContent, Element.ALIGN_CENTER, new Phrase("免费拿资料/学历免费学，扫码页脚二维码", headFont), pageRect.getWidth() / 2, headY, 0);
                if (logoImage != null){
                    logoImage.setAbsolutePosition(headX, headY);
                    underContent.addImage(logoImage);
                    underContent.saveState();
                }
                //铺满整页
//                for (int height = textH; height < pageRect.getHeight(); height = height + textH * 3) {
//                    for (int width = textW; width < pageRect.getWidth() + textW; width = width + textW * 2) {
//                        content.showTextAligned(Element.ALIGN_LEFT, text, width - textW, height - textH, 30);
//                    }
//                }
                overContent.endText();
                underContent.endText();
            }
            // 关闭
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addWaterMarkPdf(InputStream inputStream, OutputStream outputStream, String text) {
        try {
            // 原PDF文件
            PdfReader reader = new PdfReader(inputStream);
            // 输出的PDF文件内容
            PdfStamper stamper = new PdfStamper(reader, outputStream);

            // 字体 来源于 itext-asian JAR包
            BaseFont baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);

            Image logoImage = Image.getInstance(logoUrl);
            Image qrCodeImage = Image.getInstance(qrCodeUrl);

            Rectangle pageRect = null;
            PdfGState gs = new PdfGState();
            // 设置透明度
            gs.setFillOpacity(0.04f);
            gs.setStrokeOpacity(0.04f);

            PdfGState gs2 = new PdfGState();
            // 设置透明度
            gs2.setFillOpacity(1f);
            gs2.setStrokeOpacity(1f);

            int totalPage = reader.getNumberOfPages() + 1;
            for (int i = 1; i < totalPage; i++) {
                if (i == 1) {
                    continue;
                }
                pageRect = reader.getPageSizeWithRotation(i);
                //内容上层
                PdfContentByte overContent = stamper.getOverContent(i);
                //内容下层
                PdfContentByte underContent = stamper.getUnderContent(i);

                overContent.beginText();
                overContent.saveState();
                // 字体添加透明度
                overContent.setGState(gs);
                overContent.setFontAndSize(baseFont, 66);
                overContent.showTextAligned(Element.ALIGN_CENTER, text, pageRect.getWidth() / 2, pageRect.getHeight() / 2, 30);

                underContent.beginText();
                underContent.setFontAndSize(baseFont, 18);
                underContent.setGState(gs2);
                underContent.saveState();
                // 添加页脚，左侧文字，右侧页码
                ColumnText.showTextAligned(underContent, Element.ALIGN_CENTER,
                        new Phrase(String.format("关注公众号【尚德自考王者】         第 %d 页/共 %d 页          扫码关注领取免费资料", i - 1, totalPage - 2), footFont), pageRect.getWidth() / 2, 40, 0);
                if (qrCodeImage != null){
                    qrCodeImage.setAbsolutePosition(footX, footY);
                    underContent.addImage(qrCodeImage);
                    underContent.saveState();
                }

                // 添加页眉 (文字页眉，居中)
                ColumnText.showTextAligned(underContent, Element.ALIGN_CENTER, new Phrase("免费拿资料/学历免费学，扫码页脚二维码", headFont), pageRect.getWidth() / 2, headY, 0);
                if (logoImage != null){
                    logoImage.setAbsolutePosition(headX, headY);
                    underContent.addImage(logoImage);
                    underContent.saveState();
                }
                overContent.endText();
                underContent.endText();
            }
            // 关闭
            stamper.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}