package com.example.weixin.twoBarCodes;

import com.example.weixin.WxService;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Hashtable;

public class ImageUtils {
    // 海报模板media_id
    public static final String MEDIA_ID = "9uVQEsuZVw70UT8dfuNxYq4afoUbSR4pKbvZ5OuFQfcbLlzBnvvZi4stst3lPb3r";
    public static final String CHARSET = "utf8";
    public static InputStream genPosterToInputStream(String qrCodeText, String avatarUrl) throws Exception {
        BufferedImage avatar = getAvatar(avatarUrl);
//        BufferedImage big = getImageWithMediaId(avatar);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(avatar, "png", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        return is;
    }

    private static BufferedImage createQrCode(String content, boolean needCompress) throws IOException, WriterException {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 0);
        //200是定义的二维码或小图片的大小
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 200, 200, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //循环遍历每一个像素点
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

    private static BufferedImage getAvatar(String imageUrl) throws Exception {
        System.out.println("avatarUrl:" + imageUrl);
        BufferedImage avatarImage = ImageIO.read(new URL(imageUrl));
        int width = 100;
        BufferedImage formatAvatarImage = new BufferedImage(width, width, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = formatAvatarImage.createGraphics();
        //把图片切成一个圓

//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        留一个像素的空白区域，这个很重要，画圆的时候把这个覆盖
        int border = 1;
        //图片是一个圆型
        Ellipse2D.Double shape = new Ellipse2D.Double(0, 0, 100, 100);
        //需要保留的区域
        graphics.setClip(0,0,100,100);
        graphics.drawImage(avatarImage, 0, 0, 100, 100, null);
        graphics.dispose();

//        //在圆图外面再画一个圆
//        //新创建一个graphics，这样画的圆不会有锯齿
//        graphics = formatAvatarImage.createGraphics();
//        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int border1 = 3;
        //画笔是4.5个像素，BasicStroke的使用可以查看下面的参考文档
        //使画笔时基本会像外延伸一定像素，具体可以自己使用的时候测试
//        Stroke s = new BasicStroke(4.5F, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
//        graphics.setStroke(s);
        graphics.setColor(Color.WHITE);
        graphics.drawOval(0, 0, 100, 100);
        graphics.dispose();
        return formatAvatarImage;
    }

//    public static void main(String[] args) throws Exception {
////微信accessToken
//        String access_token = "";
////用户openId
//        String openId = "";
//
//        InputStream is = genPosterToInputStream("https://213.name", "http://thirdwx.qlogo.cn/mmopen/vi_32/ZZQmj4qENOHWJerYzIliasnE6NiasibY9SfIhyNeBxTlUrxaIojtjpdwU1ATbjqhbpjKbTib2XwEKiaLmaM4ialZdqyA/132");
////上传微信临时素材
//        Media media = MediaAPI.mediaUpload(access_token, Upload.MediaType.image, is);
//        System.out.println("mediaId:" + media.getMedia_id());
////        System.out.println("mediaUrl" + media.getUrl());
//
////发送文本消息
////        TextMessage textMessage2 = new TextMessage(openId, "海报测试");
////        MessageAPI.messageCustomSend(access_token, textMessage2);
////
//////发送图片消息
////        ImageMessage imageMessage = new ImageMessage(openId, media.getMedia_id());
////        MessageAPI.messageCustomSend(access_token, imageMessage);
//    }

    private static BufferedImage  getImageWithMediaId(BufferedImage avatar){
        String getImageUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        getImageUrl = getImageUrl.replace("ACCESS_TOKEN", WxService.getAccessToken()).replace("MEDIA_ID",MEDIA_ID);

        BufferedImage big = null;
        try {
            big = ImageIO.read(new URL(getImageUrl));
            BufferedImage code = createQrCode("脏凯专属", false);
            Graphics2D g = big.createGraphics();
//          画二维码
            g.drawImage(code, 510, 910, code.getWidth(), code.getHeight(), null);
//          画头像
            g.drawImage(avatar, 100, 760, 100, 100, null);
            g.dispose();
            return  big;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

}
