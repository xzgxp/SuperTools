package top.danny.tools.encrypt;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * @author huyuyang@lxfintech.com
 * @Title: AESUtil
 * @Copyright: Copyright (c) 2016
 * @Description:
 * @Company: lxjr.com
 * @Created on 2017-08-10 23:18:31
 */
public class Base64Util extends Base64 {

	/**
	 * 将 strVal进行 BASE64 编码
	 * 
	 * @param noneBase64Str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String noneBase64Str)
			throws UnsupportedEncodingException {
		try {
			return new String(Base64.encodeBase64(noneBase64Str.getBytes()),
					"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
	}

	/**
	 * 将BASE64字符串恢复为 BASE64编码前的字符串
	 * 
	 * @param base64Str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decode2Str(String base64Str)
			throws UnsupportedEncodingException {
		try {
			return new String(Base64.decodeBase64(base64Str.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			throw e;
		}
	}

}
