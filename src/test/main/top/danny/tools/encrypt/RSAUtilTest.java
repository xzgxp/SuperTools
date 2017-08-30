package top.danny.tools.encrypt;

import com.alibaba.fastjson.JSON;
import org.junit.Assert;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 * @author huyuyang@lxfintech.com
 * @Title: RSAUtilTest
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-17 17:06:45
 */
public class RSAUtilTest {

    @Test
    public void getKeysTest() throws NoSuchAlgorithmException {
        Map map=RSAUtil.getKeys();
        Assert.assertNotNull(map.get("private"));
        Assert.assertNotNull(map.get("public"));
        String privateKey=map.get("private").toString();
        String publicKey=map.get("public").toString();
        System.out.println("私钥："+privateKey);
        System.out.println("公钥："+publicKey);
    }

    @Test
    public void test() throws Exception {
        // TODO Auto-generated method stub
        HashMap<String, Object> map = RSAUtil.getKeys();
        //生成公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");
        System.out.println("公钥："+String.valueOf(Base64.encode(publicKey.getEncoded())));
        System.out.println("私钥："+String.valueOf(Base64.encode(privateKey.getEncoded())));

        //模
        String modulus = publicKey.getModulus().toString();
        //公钥指数
        String public_exponent = publicKey.getPublicExponent().toString();
        //私钥指数
        String private_exponent = privateKey.getPrivateExponent().toString();
        //明文
        String ming = "123456789";
        //使用模和指数生成公钥和私钥
        RSAPublicKey pubKey = RSAUtil.getPublicKey(modulus, public_exponent);
        RSAPrivateKey priKey = RSAUtil.getPrivateKey(modulus, private_exponent);
        //加密后的密文
        String mi = RSAUtil.encryptByPublicKey(ming, pubKey);
        System.err.println("密文："+mi);
        //解密后的明文
        ming = RSAUtil.decryptByPrivateKey(mi, priKey);
        System.err.println("明文："+ming);
    }
}
