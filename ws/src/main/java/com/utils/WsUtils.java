package com.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author by lishihao
 * @date 2019/10/19
 * DESC TODO
 */
public class WsUtils {

    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void base64StringToImage(byte[] bytes1) {
        try {
             //= decoder.decodeBuffer(base64String);
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("./a.jpg");// 可以是jpg,png,gif格式
            ImageIO.write(bi1, "jpg", w2);// 不管输出什么格式图片，此处不需改动
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        public static void flushFileByte2(String data, String filePath){
        File file = new File(filePath);
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);

        } catch (IOException e) {
            System.out.printf("写入失败"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void flushFileByte( byte[] data, String filePath){
        try {
            Files.write(Paths.get(filePath), data);
        } catch (IOException e) {
            System.out.printf("写入失败"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void flushFile( String data, String filePath){
        File file =new File(filePath);
        Writer out = null;
        try {
            out = new FileWriter(file);
            out.write(data);
            out.close();
        } catch (IOException e) {
            System.out.printf("写入失败"+e.getMessage());
            e.printStackTrace();
        }
    }

    public static void method2(String conent, String file) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(conent+"\r\n");
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] aa){
        method2("123","./a.txt");
    }

}
