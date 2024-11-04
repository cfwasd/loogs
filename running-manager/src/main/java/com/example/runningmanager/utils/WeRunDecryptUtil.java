package com.example.runningmanager.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Map;

@Component
public class WeRunDecryptUtil {

    private static final String APP_ID = "wx2110eee51f4328b4";
    private static final String APP_SECRET = "01d2990b4fcc2efa70c2ba8d682a6a6c";

    /**
     * 获取 session_key
     *
     * @param code 微信登录返回的 code
     * @return session_key
     * @throws Exception 如果请求失败或解析失败
     */
    public String getSessionKey(String code) throws Exception {
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APP_ID + "&secret=" + APP_SECRET + "&js_code=" + code + "&grant_type=authorization_code";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity());
                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
                return (String) responseMap.get("session_key");
            }
        }
    }

    /**
     * 解密微信步数数据
     *
     * @param encryptedData 加密数据
     * @param iv 初始化向量
     * @param sessionKey 会话密钥
     * @return 解密后的数据
     * @throws Exception 如果解密失败
     */
    public Map<String, Object> decryptData(String encryptedData, String iv, String sessionKey) throws Exception {
        byte[] dataByte = Base64.getDecoder().decode(encryptedData);
        byte[] ivByte = Base64.getDecoder().decode(iv);
        byte[] sessionKeyByte = Base64.getDecoder().decode(sessionKey);

        Key key = new SecretKeySpec(sessionKeyByte, "AES");
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivByte);
        cipher.init(Cipher.DECRYPT_MODE, key, ivParameterSpec);

        byte[] resultByte = cipher.doFinal(dataByte);
        String result = new String(resultByte, "UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(result, Map.class);
    }
}