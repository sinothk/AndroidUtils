package com.sinothk.android.utils;

import java.security.MessageDigest;

/**
 * 采用MD5加密解密
 *
 * @author tfq
 * @datetime 2011-10-13
 */
public class MD5Util {

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public String md5ToString(String valStr) {
        return convertMD5(convertMD5(valStr));
    }

    /***
     * MD5加码 生成32位md5码
     */
    public String stringToMD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     */
    public String convertMD5(String valStr) {

        char[] a = valStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        return new String(a);
    }

	// 测试主函数
	public void main(String args[]) {
		String s = "http://www.sinothk.com/index.html[12,2,sion]";
		System.out.println("原始：" + s);
		System.out.println("MD5后：" + md5ToString(s));
		System.out.println("加密的：" + convertMD5(s));
		System.out.println("解密的：" + convertMD5(convertMD5(s)));
	}
}

