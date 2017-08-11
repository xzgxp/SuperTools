package top.danny.tools.encrypt;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.Security;

/**
 * @author huyuyang@lxfintech.com
 * @Title: AESUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-10 23:15:31
 * 【依赖】org.bouncycastle.bcprov-jdk15on
 */
public class AESUtil {
    private static final String ALGORITHM = "AES";
    private static final String ALGORITHM_CBC = "AES/CBC/PKCS7Padding";
    private static final String DEFAULT_CHARSET = "utf-8";

    public static String aesEncryptByBouncycastle(String content, String key, String iv) throws Exception {
        try {
            byte[] value = aesEncryptByBouncycastle(content, key.getBytes(DEFAULT_CHARSET), iv.getBytes(DEFAULT_CHARSET));
            return new String(value,DEFAULT_CHARSET);
        } catch (Exception e) {
            throw new Exception("", e);
        }
    }

    public static byte[] aesEncryptByBouncycastle(String content, byte[] keyByte, byte[] ivByte) throws Exception {
        return Base64.encode( aesByBouncycastle(content, keyByte, ivByte, Cipher.ENCRYPT_MODE));
    }

    public static String aesDecryptByBouncycastle(String content, String key, String iv) throws Exception {
        try {
            byte[] value = aesByBouncycastle(Base64.decode(content), key.getBytes(DEFAULT_CHARSET), iv.getBytes(DEFAULT_CHARSET), Cipher.DECRYPT_MODE);
            return new String(value,DEFAULT_CHARSET);
        } catch (Exception e) {
            throw new Exception("", e);
        }
    }


    public static byte[] aesDecryptByBouncycastle(String content, byte[] keyByte, byte[] ivByte) throws Exception {
        return aesByBouncycastle( Base64.decode(content), keyByte, ivByte, Cipher.DECRYPT_MODE);
    }

    public static byte[] aesDecryptByBouncycastle(byte[] content, byte[] keyByte, byte[] ivByte) throws Exception {
        return aesByBouncycastle(Base64.decode(content), keyByte, ivByte, Cipher.DECRYPT_MODE);
    }

    public static byte[] aesByBouncycastle(String content, byte[] keyByte, byte[] ivByte, int model) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Key key = new SecretKeySpec(keyByte, ALGORITHM);
            Cipher in = Cipher.getInstance(ALGORITHM_CBC,"BC");
            in.init(model, key, new IvParameterSpec(ivByte));
            return in.doFinal(content.getBytes(DEFAULT_CHARSET));
        } catch (Exception e) {
            throw new Exception("", e);
        }
    }
    public static byte[] aesByBouncycastle(byte[] content, byte[] keyByte, byte[] ivByte, int model) throws Exception {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Key key = new SecretKeySpec(keyByte, ALGORITHM);
            Cipher in = Cipher.getInstance(ALGORITHM_CBC,"BC");
            in.init(model, key, new IvParameterSpec(ivByte));
            return in.doFinal(content);
        } catch (Exception e) {
            throw new Exception("", e);
        }
    }
}
