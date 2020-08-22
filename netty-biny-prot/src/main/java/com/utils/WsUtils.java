package com.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * @author by lishihao
 * @date 2019/10/19
 * DESC TODO
 */
public class WsUtils {

    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static String extCodeToType(Byte extCode) {
        Byte JPG = 100;
        Byte JPEG = 101;
        Byte PNG = 102;
        HashMap<Byte, String> extMap = new HashMap<Byte, String>();
        extMap.put(JPG, "jpg");
        extMap.put(JPEG, "jpeg");
        extMap.put(PNG, "png");
        return extMap.get(extCode);
    }

    public static void writeFile(byte[] bytes1, String ext) {
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
            BufferedImage bi1 = ImageIO.read(bais);
            File w2 = new File("./wsfile."+ext);
            ImageIO.write(bi1, ext, w2);
            System.out.println("文件上传成功！");
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

}
