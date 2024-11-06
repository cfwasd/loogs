package org.example.usermanage.utils;

import com.example.common.HttpsResult;
import com.example.common.Wxlogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

public class WxLoginHttps {
    // 响应状态码
    int statusCode;
    // 响应内容
    String responseString;

    HttpsResult httpsResult = new HttpsResult();

    public HttpsResult wiLog(String code) {
        try {
            // 构建请求 URL
            String url = "https://api.weixin.qq.com/sns/jscode2session";
            //小程序appid
            String appId = "wx58f764a104449398";
            //小程序secret
            String appSecret = "a5510da50af49c5ae513464a9d4ab321";
            // 构建 URI
            URIBuilder uriBuilder = new URIBuilder(url)
                    .addParameter("appid", appId)
                    .addParameter("secret", appSecret)
                    .addParameter("js_code", code)
                    .addParameter("grant_type", "authorization_code");
            URI uri = uriBuilder.build();

            // 创建 HttpClient
            CloseableHttpClient httpClient = createHttpClient();

            // 创建 HttpGet 请求
            HttpGet httpGet = new HttpGet(uri);

            // 发送请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
                // 获取响应状态码
                statusCode = response.getStatusLine().getStatusCode();
                httpsResult.setCode(statusCode);
                // 获取响应内容
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    responseString = EntityUtils.toString(entity);
                    System.out.println(responseString);
                    try {
                        // 使用 Jackson 解析 JSON 响应
                        ObjectMapper objectMapper = new ObjectMapper();
                        Wxlogin wxlogin = objectMapper.readValue(responseString, Wxlogin.class);
                        httpsResult.setWxlogin(wxlogin);
                    }catch (Exception e){
                        httpsResult.setMsg(responseString);
                        return httpsResult;
                    }
                }else {
                    responseString = "无响应内容，请检查路径";
                }
            }
        } catch (URISyntaxException | IOException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
        return httpsResult;
    }

    // 创建 HttpClient，忽略 SSL 证书验证（仅用于测试环境）
    private static CloseableHttpClient createHttpClient() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        SSLContextBuilder builder = new SSLContextBuilder();
        builder.loadTrustMaterial(null, (chain, authType) -> true);
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(), NoopHostnameVerifier.INSTANCE);
        return HttpClientBuilder.create().setSSLSocketFactory(sslsf).build();
    }
}