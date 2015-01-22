package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;


public final class Md5Compute {
	public static String  getmd5(String key) throws NoSuchAlgorithmException{
		MessageDigest md=MessageDigest.getInstance("md5");
		byte[] b=md.digest(key.getBytes());
		BASE64Encoder base=new BASE64Encoder();
		return base.encode(b);
	}
}
