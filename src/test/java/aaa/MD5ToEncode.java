package aaa;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import Decoder.BASE64Encoder;

public class MD5ToEncode {

	/*
	 * public String EncoderByMd5(String str) {
	 * 
	 * MessageDigest md5=MessageDigest.getInstance("MD5"); BASE64Encoder
	 * base64en = new BASE64Encoder();
	 * 
	 * String newstr=base64en.encode(md5.digest(str.getBytes("utf-8"))); return
	 * newstr; }
	 */

	/*
	 * public String EncoderByMd5(String pwd) { String hexString = ""; try {
	 * System.out.println("...................."); // 1指定算法类型； MessageDigest
	 * digest = MessageDigest.getInstance("MD5"); // 2将需要加密的字符串转换成byte数组； byte[]
	 * bs = digest.digest(pwd.getBytes()); // 3通过遍历bs 生成32位的字符串；
	 * System.out.println("密码值。。。。。" + bs.toString()); // 最后字符串有个拼接的过程；
	 * StringBuffer sb = new StringBuffer(); for (byte b : bs) { int i = b &
	 * 0xff; // int 类型的i 是4个字节占32位； // int 类型的i转换成16进制字符； hexString =
	 * Integer.toHexString(i); if (hexString.length() < 2) {//
	 * 补零的过程，因为生成的时候有的是一位有的是两位所以需要有个补零的过程； hexString = "0" + hexString; }
	 * sb.append(hexString); }
	 * 
	 * System.out.println(sb.toString());
	 * 
	 * } catch (NoSuchAlgorithmException e) {// 找不到指定算法的错误； // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return hexString;
	 * 
	 * 
	 * }
	 */

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

}
