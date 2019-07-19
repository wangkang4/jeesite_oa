package com.thinkgem.jeesite.modules.mobile.test.web;

import com.thinkgem.jeesite.common.security.Digests;
import com.thinkgem.jeesite.common.utils.Encodes;

import java.text.DateFormat;
import java.util.Date;

public class Test {

    public static String entryptPassword(String plainPassword) {
        String plain = Encodes.unescapeHtml ( plainPassword );
        byte[] salt = Digests.generateSalt ( 8 );
        byte[] hashPassword = Digests.sha1 ( plain.getBytes (), salt, 1024 );
        return Encodes.encodeHex ( salt ) + Encodes.encodeHex ( hashPassword );
    }


    public static boolean validatePassword(String plainPassword, String password) {
        String plain = Encodes.unescapeHtml ( plainPassword );
        byte[] salt = Encodes.decodeHex ( password.substring ( 0, 16 ) );
        byte[] hashPassword = Digests.sha1 ( plain.getBytes (), salt, 1024 );
        return password.equals ( Encodes.encodeHex ( salt ) + Encodes.encodeHex ( hashPassword ) );
    }


    public static void main(String[] args) {


//			System.out.println(entryptPassword("123456")); 

//			System.out.println(validatePassword("123","007cd1ea2ee1902169cafc7c847bf6a698828e2faacaa45f14ac9281"));

        test1 ();
    }

    public static void test1() {
        Date date = new Date ();
        DateFormat df1 = DateFormat.getDateInstance ();//日期格式，精确到日
        System.out.println ( df1.format ( date ) );
    }

}
