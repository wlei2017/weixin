package com.example.weixin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtils {
    private static CloseableHttpClient httpclient;

    static {
        PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();
        manager.setMaxTotal(200); //连接池最大并发连接数
        manager.setDefaultMaxPerRoute(200);//单路由最大并发数,路由是对maxTotal的细分
        httpclient = HttpClients.custom().setConnectionManager(manager).build();
    }

    private static RequestConfig config = RequestConfig.copy(RequestConfig.DEFAULT)
            .setSocketTimeout(10000)
            .setConnectTimeout(5000)
            .setConnectionRequestTimeout(100).build();
    // .setProxy(new HttpHost("127.0.0.1",8888,"http")).build();


    public static String doGet(String url, Map<String, Object> header)
            throws Exception {
        String ret = "";
        HttpGet get = new HttpGet(url);
        get.setConfig(config);
        get.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        CloseableHttpResponse closeableHttpResponse = null;
        try {
            if (header != null) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    get.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            closeableHttpResponse = httpclient.execute(get);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            } else {
                throw new Exception(
                        "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode() + "].");
            }
        } catch (ClientProtocolException e) {
            throw new Exception("HttpClient error," + e.getMessage());
        } catch (IOException e) {
            throw new Exception("IO error," + e.getMessage());
        } finally {
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }


    public static String doPost(String url, Map<String, Object> params, Map<String, Object> header)
            throws Exception {
        String ret = "";
        HttpPost post = new HttpPost(url);
        post.setConfig(config);
        post.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        CloseableHttpResponse closeableHttpResponse = null;
        HttpEntity postEntity = null;
        try {
            if (params != null) {
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                postEntity = new UrlEncodedFormEntity(list);
                post.setEntity(postEntity);
            }

            if (header != null) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            closeableHttpResponse = httpclient.execute(post);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            } else {
                throw new Exception(
                        "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode() + "].");
            }
        } catch (ClientProtocolException e) {
            throw new Exception("HttpClient error," + e.getMessage());
        } catch (IOException e) {
            throw new Exception("IO error," + e.getMessage());
        } finally {
            if (postEntity != null) {
                try {
                    EntityUtils.consume(postEntity);
                } catch (IOException e) {
                }
            }
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }


    public static String doPost(String url, String params, Map<String, Object> header)
            throws Exception {
        String ret = "";
        HttpPost post = new HttpPost(url);
        post.setConfig(config);
        post.addHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        CloseableHttpResponse closeableHttpResponse = null;
        HttpEntity postEntity = null;
        try {
            if (params != null) {
                JSONObject jsonObject = JSONObject.parseObject(params);
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                for (Map.Entry<String, Object> entry : jsonObject.entrySet()) {
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
                }
                postEntity = new UrlEncodedFormEntity(list);
                post.setEntity(postEntity);
            }

            if (header != null) {
                for (Map.Entry<String, Object> entry : header.entrySet()) {
                    post.setHeader(entry.getKey(), entry.getValue().toString());
                }
            }
            closeableHttpResponse = httpclient.execute(post);
            if (closeableHttpResponse.getStatusLine().getStatusCode() == 200) {
                ret = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            } else {
                throw new Exception(
                        "System level error, Code=[" + closeableHttpResponse.getStatusLine().getStatusCode() + "].");
            }
        } catch (ClientProtocolException e) {
            throw new Exception("HttpClient error," + e.getMessage());
        } catch (IOException e) {
            throw new Exception("IO error," + e.getMessage());
        } finally {
            if (postEntity != null) {
                try {
                    EntityUtils.consume(postEntity);
                } catch (IOException e) {
                }
            }
            if (closeableHttpResponse != null) {
                try {
                    closeableHttpResponse.close();
                } catch (IOException e) {
                }
            }
        }
        return ret;
    }


    public static String sendHttpPost(String url, String body) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Accept", "application/json");
        httpPost.setEntity(new StringEntity(body, Charsets.UTF_8));
        CloseableHttpResponse response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode() + "\n");
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        response.close();
        httpClient.close();
        return responseContent;
    }

    public static String post(String url,String data){
        try {
            URL urlObj = new URL(url);
            URLConnection connection = urlObj.openConnection();
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(data.getBytes());
            os.close();
            InputStream is = connection.getInputStream();
            byte[] b = new byte[1024];
            int len;
            StringBuffer sb = new StringBuffer();
            while ((len = is.read(b)) != -1){
                sb.append(new String(b,0,len));

            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
