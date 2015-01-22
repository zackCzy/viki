package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public final class WebUtils {
    public static <T> T register2Bean(HttpServletRequest request,Class<T> beanClass){   	
    	try {
    		T bean=beanClass.newInstance();
			BeanUtils.populate(bean,request.getParameterMap());	
			return bean;
		} catch (Exception e) {
			throw new RuntimeException();
		}		
    }
    
	public static String getToken(){		
		String token=System.currentTimeMillis()+Math.random()+"";
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			byte [] b=md.digest(token.getBytes());
			return token=Base64.encode(b);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}		
	}
}
