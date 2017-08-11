package top.danny.tools.string;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

/**
 * @author huyuyang@lxfintech.com
 * @Title: CompressStringUtil
 * @Copyright: Copyright (c) 2016
 * @Description: 字符串压缩（zip、gzip），字符重复度越高，压缩程度越高
 * 不需要依赖除jdk之外的jar包
 * @Company: lxjr.com
 * @Created on 2016-11-27 22:56:32
 */
public class CompressStringUtil {

    public static void main(String[] args) {
        StringBuffer baseStr = new StringBuffer("");
        baseStr.append("DannyHooDannyHooDannyHooDannyHooDannyHooD1annyHooDannyHooDannyHooDannyHoo");
        baseStr.append("DannyHooDannyHooDannyHooDannyHo#oDannyHooDannyHooDannyHooDannyHooDannyHoo");
        baseStr.append("DannyHooDannyHooDannyHooDannyHooDannyHooDannyHooDannyHooDannyHooDannyHoo");
        baseStr.append("DannyHooDannyHooDannyHooD%#annyHooDan$nyHooDannyHooDannyHooDannyHooDannyHoo");
        baseStr.append("DannyHooDannyHooDannyHooDannyHooDannyHooDannyHooDannyHooDannyHooDannyHoo");
        baseStr.append("DannyHooDannyHooDanny3HooDannyHooDan66nyHooDannyHooDann6yHooDannyHooDannyHoo");
        System.out.println("zip:\n" + gzip(baseStr.toString()));
    }

    /**
     * 使用gzip进行压缩
     *
     * @param primStr 需要进行压缩的字符串
     * @return 被gzip解压缩后的字符串
     */
    public static String gzip(String primStr) {
        if (primStr == null || primStr.length() == 0) {
            return primStr;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(primStr.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (gzip != null) {
                try {
                    gzip.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return new sun.misc.BASE64Encoder().encode(out.toByteArray());
    }

    /**
     * 使用gzip进行解压缩
     *
     * @param compressedStr 需要进行解压缩的字符串
     * @return 被gzip解压缩后的字符串
     */
    public static String gunzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        byte[] compressed = null;
        String decompressed = null;
        try {
            compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);

            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ginzip != null) {
                try {
                    ginzip.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }

        return decompressed;
    }

    /**
     * 使用zip进行压缩
     *
     * @param str 需要进行压缩的字符串
     * @return 被zip压缩后的字符串
     */
    public static final String zip(String str) {
        if (str == null)
            return null;
        byte[] compressed;
        ByteArrayOutputStream out = null;
        ZipOutputStream zout = null;
        String compressedStr = null;
        try {
            out = new ByteArrayOutputStream();
            zout = new ZipOutputStream(out);
            zout.putNextEntry(new ZipEntry("0"));
            zout.write(str.getBytes());
            zout.closeEntry();
            compressed = out.toByteArray();
            compressedStr = new sun.misc.BASE64Encoder().encodeBuffer(compressed);
        } catch (IOException e) {
            compressed = null;
        } finally {
            if (zout != null) {
                try {
                    zout.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return compressedStr;
    }

    /**
     * 使用zip进行解压缩
     *
     * @param compressedStr 需要进行解压缩的字符串
     * @return 被zip解压缩后的字符串
     */
    public static final String unzip(String compressedStr) {
        if (compressedStr == null) {
            return null;
        }

        ByteArrayOutputStream out = null;
        ByteArrayInputStream in = null;
        ZipInputStream zin = null;
        String decompressed = null;
        try {
            byte[] compressed = new sun.misc.BASE64Decoder().decodeBuffer(compressedStr);
            out = new ByteArrayOutputStream();
            in = new ByteArrayInputStream(compressed);
            zin = new ZipInputStream(in);
            zin.getNextEntry();
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = zin.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }
            decompressed = out.toString();
        } catch (IOException e) {
            decompressed = null;
        } finally {
            if (zin != null) {
                try {
                    zin.close();
                } catch (IOException e) {
                }
            }
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                }
            }
        }
        return decompressed;
    }
}
