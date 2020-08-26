package com.shawf.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author shawf_lee
 * @date 2020/5/26 16:51
 * Content:
 */
public class MD5Util {
    /**
     * MD5加密方法
     * @param context 要加密的字符串
     * @return 加密后的字符串
     */
    public static String encrypByMd5(String context) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(context.getBytes());//update处理
            byte [] encryContext = md.digest();//调用该方法完成计算

            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < encryContext.length; offset++) {//做相应的转化（十六进制）
                i = encryContext[offset];
                if (i < 0) {i += 256;}
                if (i < 16) {buf.append("0");}
                buf.append(Integer.toHexString(i));
            }
            //System.out.println("32result: " + buf.toString());// 32位的加密
            //System.out.println("16result: " + buf.toString().substring(8, 24));// 16位的加密
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.printf(new MD5Util().encrypByMd5("123456"));
//    }
}
