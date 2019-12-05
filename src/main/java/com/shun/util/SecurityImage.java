package com.shun.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Random;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class SecurityImage {
    /**
     * 生成验证码图片
     *
     * @param securityCode
     *
     * @return
     *
     */
    public static BufferedImage createImage(String securityCode) {

        int codeLength = securityCode.length();// 验证码长度

        int fontSize = 15;// 字体大小

        int fontWidth = fontSize + 1;

        // 图片宽高

        int width = codeLength * fontWidth + 6;

        int height = fontSize * 2 + 1;

        // 图片

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);// 设置背景色

        g.fillRect(0, 0, width, height);// 填充背景

        g.setColor(Color.LIGHT_GRAY);// 设置边框颜色

        g.setFont(new Font("Arial", Font.BOLD, height - 2));// 边框字体样式

        g.drawRect(0, 0, width - 1, height - 1);// 绘制边框

        // 绘制噪点

        Random rand = new Random();

        g.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < codeLength * 6; i++) {

            int x = rand.nextInt(width);

            int y = rand.nextInt(height);

            g.drawRect(x, y, 1, 1);// 绘制1*1大小的矩形

        }

        // 绘制验证码

        int codeY = height - 10;

        g.setColor(new Color(19, 148, 246));

        g.setFont(new Font("Georgia", Font.BOLD, fontSize));
        for (int i = 0; i < codeLength; i++) {
            double deg = new Random().nextDouble() * 20;
            g.rotate(Math.toRadians(deg), i * 16 + 13, codeY - 7.5);
            g.drawString(String.valueOf(securityCode.charAt(i)), i * 16 + 5, codeY);
            g.rotate(Math.toRadians(-deg), i * 16 + 13, codeY - 7.5);
        }

        g.dispose();// 关闭资源

        return image;

    }

    /**
     *
     * 返回验证码图片的流格式
     *
     * @param securityCode
     *
     * @return
     *
     */

    private static ByteArrayInputStream getImageAsInputStream(String securityCode) {

        BufferedImage image = createImage(securityCode);

        return convertImageToStream(image);

    }

    /**
     *
     * 将BufferedImage转换成ByteArrayinputStream
     *
     * @param image
     *
     * @return
     *
     */

    private static ByteArrayInputStream convertImageToStream(BufferedImage image) {

        ByteArrayInputStream inputStream = null;

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);

        try {

            encoder.encode(image);

            byte[] b = outStream.toByteArray();

            inputStream = new ByteArrayInputStream(b);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputStream;

    }

}

