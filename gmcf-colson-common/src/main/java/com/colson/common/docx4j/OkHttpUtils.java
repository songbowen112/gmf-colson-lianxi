package com.sunlands.analyze.docx4j;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 类描述
 *
 * @Author: 吴雨佳
 * @since: 2018/2/28 15:57
 * @update: [变更日期yyyy-MM-dd][变更人姓名][变更描述]
 */
public class OkHttpUtils {

    private static Logger logger = LoggerFactory.getLogger(OkHttpUtils.class);

    /**
     * 根据url下载文件, 并转换为byte数组
     * @param url   请求url
     * @return
     */
    public static byte[] getBytesFromUrl(String url) throws IOException {
        Request.Builder builder = new Request.Builder().url(url);
        return execute(builder).body().bytes();
    }

    /**
     * 执行请求的方法
     * @param builder       请求构建器
     * @return              返回请求响应
     * @throws IOException  如果请求失败抛出IO异常
     */
    private static Response execute(Request.Builder builder) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = builder.build();
        Call call = client.newCall(request);
        try {
            Response response = call.execute();
            logger.info("请求成功: {}", request.toString());
            return response;
        } catch (IOException e) {
            logger.error("请求失败, Request:{} , message: {}", request.toString(), e.getMessage());
            throw e;
        }
    }
}
