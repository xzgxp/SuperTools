package top.danny.tools.file.io;

import java.io.*;

/**
 * @author huyuyang@lxfintech.com
 * @Title: BinaryFileConvert
 * @Copyright: Copyright (c) 2016
 * @Description: http://www.educity.cn/wenda/367640.html
 * @Company: lxjr.com
 * @Created on 2017-06-20 10:28:21
 */
public class BinaryFileConvert {
    public static void main(String[] args) {
        String filePath = "/Users/dannyhoo/Downloads/表设计.xlsx";
        byte[] bytes = getFileToByte(filePath);
        System.out.println(bytes);
        byte2File(bytes,"/Users/dannyhoo/Downloads/","byte_test.txt");
    }

    public static byte[] getFileToByte(String filePath) {
        byte[] bytes = null;
        try {
            File file = new File(filePath);
            InputStream is = new FileInputStream(file);
            ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
            bytes = new byte[(int) file.length()];
            byte[] bb = new byte[2048];
            int ch;
            ch = is.read(bb);
            while (ch != -1) {
                bytestream.write(bb, 0, ch);
                ch = is.read(bb);
            }
            bytes = bytestream.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }

    public static String getByteToFile(byte[] bytes) throws FileNotFoundException {
        OutputStream out = null;

        out = new FileOutputStream("b.txt");
        String str = "java终于完了";
        byte[] b = str.getBytes();
        try {
            out.write(b, 0, b.length);
        } catch (IOException ex1) {
            ex1.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex2) {
                ex2.printStackTrace();
            }
        }
        return "b.txt";
    }

    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
