package com.internation.info.Config;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Repository;

@Repository
public class AddMD5Encode {
	public static String md5Pwd(String password, String username) {
		//加密方式
		String hashAlgorithmName = "MD5";
		ByteSource salt = ByteSource.Util.bytes(username);
		int hashIterations = 2;// 散列2次
		String  hashStr = new SimpleHash(hashAlgorithmName, password,salt,hashIterations).toHex();
		return hashStr;
	}
}
