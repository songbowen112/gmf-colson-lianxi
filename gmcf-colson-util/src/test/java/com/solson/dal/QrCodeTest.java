package com.solson.dal;


import com.colson.util.qrcode.QRCodeUtil;

public class QrCodeTest {

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "❥(^_-)爱你哟";
        String picture = "https://c.aiiz.cn/M4sfR2" +
                "\n公司名称：xxxx" +
                "\n注册工商号：xxxx";
        // https://www.baidu.com/

        // 直接存储 HTML 内容（适合简单图文）
        String htmlContent = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "    <title>扫描结果</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>产品介绍</h1>\n" +
                "    <img src=\"product.jpg\" width=\"300\">\n" +
                "    <p>这是一段详细的图文描述...</p>\n" +
                "</body>\n" +
                "</html>";
        // 嵌入二维码的图片路径
        String imgPath = "C:/Users/hp/Desktop/timg.jpg";

        // 生成的二维码的路径及名称
//        String destPath = "C:/Users/hp/Desktop/qrCode/timg.jpg";
        String destPath = "/Users/man/Desktop/timer.jpg";

        //生成二维码
//        QRCodeUtil.encode(text, imgPath, destPath, true);
//        QRCodeUtil.encode(htmlContent, destPath);
        QRCodeUtil.encode(picture, destPath);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

}
