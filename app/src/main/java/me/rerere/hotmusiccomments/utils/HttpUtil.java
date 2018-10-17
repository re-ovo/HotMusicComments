package me.rerere.hotmusiccomments.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtil {
    /**
     * 通过HTTPS连接获取网页内容
     * 主要用于访问API接口地址
     *
     * @param link Https连接
     * @return 网页内容
     */
    public static String connect(String link) {
        try {
            // 创建URL
            URL url = new URL(link);
            // 打开连接
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            // 设置UA，防止被防火墙阻断
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            // 连接开始
            connection.connect();
            // 获得连接输入流
            InputStream inputStream = connection.getInputStream();
            InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            // 开始遍历流，读取数据，写入StringBuffer
            String temp;
            final StringBuffer sb = new StringBuffer();

            while ((temp = br.readLine()) != null) {
                sb.append("\n");
                sb.append(temp);
            }
            // 关闭流
            br.close();
            // 返回结果
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
