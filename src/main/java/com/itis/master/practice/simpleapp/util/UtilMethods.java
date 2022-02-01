package com.itis.master.practice.simpleapp.util;

import org.apache.commons.codec.digest.DigestUtils;

/*****
 * @author Igor Astafyev
 * June 2019
 * Additional methods for app
 *****/

public class UtilMethods {

	public static String createKey(String email) {
		String key = DigestUtils.md5Hex(email);
		key = DigestUtils.md5Hex(key) + key;
		return key;
	}

}