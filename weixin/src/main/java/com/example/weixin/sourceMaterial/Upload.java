package com.example.weixin.sourceMaterial;

import com.alibaba.fastjson.JSONObject;
import com.example.weixin.WxService;
import com.example.weixin.twoBarCodes.GenerateCode;
import com.example.weixin.twoBarCodes.ImageUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import sun.misc.BASE64Encoder;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 上传素材
 */
public class Upload {


    /**
     * 上传临时素材
     *
     * @param path 上传的文件路径
     * @param type 上传的文件类型
     * @return
     */
    public static String uploadTemparary(String path, String type) {

        File file = new File(path);
        String url = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        url = url.replace("ACCESS_TOKEN", WxService.getAccessToken()).replace("TYPE", type);

        try {
            URL urlObj = new URL(url);
            //强转为安全连接
            HttpsURLConnection conn = (HttpsURLConnection)urlObj.openConnection();
            //设置连接的信息
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            //设置请求头信息
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Charset", "utf8");
            //数据的边界
            String boundary = "-----" + System.currentTimeMillis();
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            //第一部分 头部信息
            //准备头部信息
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(boundary);
            sb.append("\r\n");
            sb.append("Content-Disposition:form-data;name=\"media\";filename=\"" + file.getName() + "\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            //获取输出流
            OutputStream out = conn.getOutputStream();
            //获取输入流
            InputStream is = conn.getInputStream();
            out.write(sb.toString().getBytes());
            System.out.println(sb.toString());
            //第二部分 文件内容
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                out.write(bytes, 0, len);
            }
            //第三部分 尾部信息
            String foot = "\r\n--" + boundary + "--\r\n";
            out.write(foot.getBytes());
            out.flush();
            out.close();
            //读取数据
            InputStream is2 = conn.getInputStream();
            StringBuilder resp = new StringBuilder();
            while ((len = is2.read(bytes)) != -1) {
                resp.append(new String(bytes, 0, len));
            }
            return resp.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static JSONObject UploadMeida(String filePath,String urlString) throws Exception{
        //返回结果
        String result=null;
        File file=new File(filePath);
        if(!file.exists()||!file.isFile()){
            throw new IOException("文件不存在");
        }
//        String token=WxService.getAccessToken();
//        String urlString="https://api.weixin.qq.com/cgi-bin/media/upload?access_token="+token+"&type="+fileType;
        URL url=new URL(urlString);
        HttpsURLConnection conn=(HttpsURLConnection) url.openConnection();
        conn.setRequestMethod("POST");//以POST方式提交表单
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);//POST方式不能使用缓存
        //设置请求头信息
        conn.setRequestProperty("Connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        //设置边界
        String BOUNDARY="----------"+System.currentTimeMillis();
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
        //请求正文信息
        //第一部分
        StringBuilder sb=new StringBuilder();
        sb.append("--");//必须多两条道
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\"; filename=\"" + file.getName()+"\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        System.out.println("sb:"+sb);

        //获得输出流
        OutputStream out=new DataOutputStream(conn.getOutputStream());
        //输出表头
        out.write(sb.toString().getBytes("UTF-8"));
        //文件正文部分
        //把文件以流的方式 推送道URL中
        DataInputStream din=new DataInputStream(new FileInputStream(file));
        int bytes=0;
        byte[] buffer=new byte[1024];
        while((bytes=din.read(buffer))!=-1){
            out.write(buffer,0,bytes);
        }
        din.close();
        //结尾部分
        byte[] foot=("\r\n--" + BOUNDARY + "--\r\n").getBytes("UTF-8");//定义数据最后分割线
        out.write(foot);
        out.flush();
        out.close();
        if(HttpsURLConnection.HTTP_OK==conn.getResponseCode()){

            StringBuffer strbuffer=null;
            BufferedReader reader=null;
            try {
                strbuffer=new StringBuffer();
                reader=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String lineString=null;
                while((lineString=reader.readLine())!=null){
                    strbuffer.append(lineString);

                }
                if(result==null){
                    result=strbuffer.toString();
                    System.out.println("result:"+result);
                }
            } catch (IOException e) {
                System.out.println("发送POST请求出现异常！"+e);
                e.printStackTrace();
            }finally{
                if(reader!=null){
                    reader.close();
                }
            }

        }
        JSONObject jsonObject=JSONObject.parseObject(result);
        return jsonObject;

    }


}
