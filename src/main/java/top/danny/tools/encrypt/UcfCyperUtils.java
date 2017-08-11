package top.danny.tools.encrypt;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouyarui@zhulebei.com
 * @Title UcfCyperUtils
 * @Copyright: Copyright (c) 2016
 * @Description: <br>
 * @Company: zhulebei.com
 * @Created on 16/2/4
 */
public class UcfCyperUtils {
    private static final String Algorithm = "DESede";
    private static final String Algorithm_CBC = "DESede/CBC/PKCS5Padding";
    private static final String PASSWORD_CRYPT_KEY = "2016UcfPwd010ForBeijingJRGC62880088";
    private static List<SecretKey> deskeys = new ArrayList<SecretKey>();
    private static byte counter;


    static public class EncryptItem {
        String query;
        long miliTime;
        public EncryptItem(String query, long miliTime) {
            this.query = query;
            this.miliTime = miliTime;
        }

        public String getQuery() {
            return query;
        }

        public long getMiliTime() {
            return miliTime;
        }

    }

    public static boolean setSecretKeys(List<String> keys) throws Exception {
        if (keys == null || keys.isEmpty()) {
            return false;
        }

        List<SecretKey> tmpKeys = new ArrayList<SecretKey>();
        for (String key : keys) {
            SecretKey deskey = new SecretKeySpec(build3DesKey(key), Algorithm);
            tmpKeys.add(deskey);
        }
        deskeys = tmpKeys;
        return true;
    }

    /**
     * 加密方法
     * @param src 源数据的字节数组
     * @return
     */
    private static byte[] encryptMode(byte[] src, int key) throws Exception {
        //SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY), Algorithm);    //生成密钥
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        Cipher c1 = Cipher.getInstance(Algorithm_CBC);    //实例化负责加密/解密的Cipher工具类
        c1.init(Cipher.ENCRYPT_MODE, deskeys.get(key), iv);    //初始化为加密模式
        return c1.doFinal(src);
    }


    /**
     * 解密函数
     * @param src 密文的字节数组
     * @return
     */
    private static byte[] decryptMode(byte[] src, int key) throws Exception {
        //SecretKey deskey = new SecretKeySpec(build3DesKey(PASSWORD_CRYPT_KEY),
        //        Algorithm);
        IvParameterSpec iv = new IvParameterSpec(new byte[8]);
        Cipher c1 = Cipher.getInstance(Algorithm_CBC);
        c1.init(Cipher.DECRYPT_MODE, deskeys.get(key), iv); // 初始化为解密模式
        return c1.doFinal(src);
    }



    /*
     * 根据字符串生成密钥字节数组
     * @param keyStr 密钥字符串
     * @return
     * @throws UnsupportedEncodingException
     */
    private static byte[] build3DesKey(String keyStr) throws UnsupportedEncodingException {
        byte[] key = new byte[24];    //声明一个24位的字节数组，默认里面都是0
        byte[] temp = keyStr.getBytes("UTF-8");    //将字符串转成字节数组

        /*
         * 执行数组拷贝
         * System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
         */
        if(key.length > temp.length){
            //如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, temp.length);
        }else{
            //如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
            System.arraycopy(temp, 0, key, 0, key.length);
        }
        return key;
    }

    private static byte[] longToByteArray(long value) {
        byte[] result = new byte[8];
        result[0] = (byte) (value >> 56);
        result[1] = (byte) (value >> 48);
        result[2] = (byte) (value >> 40);
        result[3] = (byte) (value >> 32);
        result[4] = (byte) (value >> 24);
        result[5] = (byte) (value >> 16);
        result[6] = (byte) (value >> 8);
        result[7] = (byte) value;
        return result;
    }

    private static long byteArrayToLong(byte[] bytes) {
        long l = ((bytes[0] & 0xFF) << 24) | ((bytes[1] & 0xFF) << 16)
                | ((bytes[2] & 0xFF) << 8) | (bytes[3] & 0xFF);
        int i = ((bytes[4] & 0xFF) << 24) | ((bytes[5] & 0xFF) << 16)
                | ((bytes[6] & 0xFF) << 8) | (bytes[7] & 0xFF);

        return (l << 32) | (i & 0xFFFFFFFFL);
    }


    public static String encrypt(String query, int key) throws Exception {
        if (key >= deskeys.size()) {
            throw new Exception("key is not exist");
        }

        Long milisec = System.currentTimeMillis();
        counter++;
        byte[] head = longToByteArray(milisec);
        byte[] body = query.getBytes();
        byte[] all = new byte[9 + body.length];
        System.arraycopy(head, 0, all, 0, 8);
        all[9] = counter;
        System.arraycopy(body, 0, all, 9, body.length);
        byte[] encryptArr = encryptMode(all, key);
        return new String(Base64.encodeBase64(encryptArr));

    }

    public static EncryptItem decrypt(String query, int key) throws Exception {
        if (key >= deskeys.size()) {
            throw new Exception("key is not exist");
        }

        byte[] encryptAll = Base64.decodeBase64(query.getBytes());
        byte[] all = decryptMode(encryptAll, key);
        if (all.length < 9) {
            throw new Exception("decodeBase64 string's length < 9");
        }
        int bodyLen = all.length-9;
        byte[] head = new byte[8];
        byte[] body = new byte[bodyLen];
        System.arraycopy(all, 0, head, 0, 8);
        System.arraycopy(all, 9, body, 0, bodyLen);
        return new EncryptItem(new String(body) ,byteArrayToLong(head));
    }


    /**
     * @param args
     */
    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 2; i++) {
            String msg = "12309876543"
                    + i;
            System.out.println("【加密前】：" + msg);
            // System.out.println("【加密前】：" + msg.length());

            try {
                List<String> array = new ArrayList<String>();
                array.add(UcfCyperUtils.PASSWORD_CRYPT_KEY);
                UcfCyperUtils.setSecretKeys(array);
                // 加密
                String encrptStr = UcfCyperUtils.encrypt(msg, 0);

                System.out.println("【加密后】：" + encrptStr);
                // System.out.println("【加密后】：" + encrptStr.length());

                // 解密
                EncryptItem item = UcfCyperUtils.decrypt(encrptStr, 0);
                System.out.println("【解密后】：" + item.query + "; miliTime=" + item.miliTime);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("elapse time:"+(end-start));
    }

}
