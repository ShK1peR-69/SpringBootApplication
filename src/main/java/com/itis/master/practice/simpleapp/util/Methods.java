package com.itis.master.practice.simpleapp.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Arrays;

/*****
 * @author Igor Astafyev
 * June, 2019
 * Additional methods for app
 *****/

public class Methods {

    public static String hashPass(String password) {
        password = "first_sold" + password;
        password = Arrays.toString(DigestUtils.sha256(password));
        password += "second_sold";
        password = DigestUtils.md5Hex(password);
        return password;
    }

    public static String createKey(String email) {
        String key = DigestUtils.md5Hex(email);
        key = DigestUtils.md5Hex(key) + key;
        return key;
    }

}
