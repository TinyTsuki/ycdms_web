package cn.antraces.test;

import cn.antraces.dms.utils.AESUtil;
import cn.antraces.dms.utils.MD5Util;

public class Test {
    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 3;

        System.out.println(System.currentTimeMillis());

        String s = AESUtil.encrypt(MD5Util.getMD5Code_16("123") +
                MD5Util.getMD5Code_16(String.valueOf(timestamp)), "silvermoonant" + "qzhJNRkcxGkxcaLocvbmiGwi");

        System.out.println(System.currentTimeMillis());

        s = MD5Util.getMD5Code_16(s.substring(0, 32)) +
                MD5Util.getMD5Code_16(s.substring(32, 64)) +
                MD5Util.getMD5Code_16(s.substring(64));

        System.out.println(System.currentTimeMillis());

        System.out.println(s);

    }
}
