package com.solson.dal;


import com.colson.util.qrcode.QRCodeUtil;

public class QrCodeTest {

    public static void main(String[] args) throws Exception {
        // 存放在二维码中的内容
        String text = "❥(^_-)爱你哟";
        // https://www.baidu.com/

        // 直接存储 HTML 内容（适合简单图文）
        String htmlContent = "<div style='text-align:center'>" +
                "<h1>产品名称</h1>" +
                "<img src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAA...'/>" + // Base64 图片
                "<p>产品描述...</p></div>";
        // 嵌入二维码的图片路径
        String imgPath = "C:/Users/hp/Desktop/timg.jpg";
        // 生成的二维码的路径及名称
//        String destPath = "C:/Users/hp/Desktop/qrCode/timg.jpg";
        String destPath = "/Users/man/Desktop/timer.jpg";
        //生成二维码
//        QRCodeUtil.encode(text, imgPath, destPath, true);
        QRCodeUtil.encode(htmlContent, destPath);
        // 解析二维码
        String str = QRCodeUtil.decode(destPath);
        // 打印出解析出的内容
        System.out.println(str);
    }

}
