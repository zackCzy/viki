package Utils;

import java.io.InputStream;
import java.net.URLEncoder;
import java.text.MessageFormat;

import org.apache.commons.io.IOUtils;


/**
 * <pre>
 * ∑≠“Îπ§æﬂ
 * PS: Õ∏ﬂ^google translate
 * </pre>
 * 
 * @author zackO
 * @version 1.0
 * */
public class Translator {
	String ENCODING = "UTF-8";
	
	public String quicklyWord(String text, String from_lang, String target_lang)
			throws Exception {
		String URL_TEMPLATE = "http://brisk.eu.org/api/translate.php?from={0}&to={1}&text={2}";
		InputStream is = null;
		try {
			String url = MessageFormat.format(URL_TEMPLATE, from_lang,
					target_lang, URLEncoder.encode(text, ENCODING));
			is = HttpClientUtil.downloadAsStream(url);
			byte b[] = new byte[1024];
			int i = 0;
			String result = "";
			while ((i = is.read(b)) != -1) {
				result += new String(b, 0, i);
			}
			return result;
		} finally {
			IOUtils.closeQuietly(is);
			is = null;
		}
	}
	
	public String youDaoTrans(String text, String from_lang, String target_lang)
			throws Exception {
		String URL_TEMPLATE = "http://translate.google.cn/translate_a/t?client=t&hl=zh-CN&sl={0}&tl={1}&text={2}";
		InputStream is = null;
		try {
			String url = MessageFormat.format(URL_TEMPLATE, from_lang,
					target_lang, URLEncoder.encode(text, ENCODING));
			is = HttpClientUtil.downloadAsStream(url);
			byte b[] = new byte[1024];
			int i = 0;
			String result = "";
			while ((i = is.read(b)) != -1) {
				result += new String(b, 0, i);
			}
			return result;

		} finally {
			IOUtils.closeQuietly(is);
			is = null;
		}
	}
	
	public String yueWord(String text,String from_lang,String target_lang) throws Exception {
		InputStream is = null;
		try {
			String url = "http://fanyi.baidu.com/v2transapi?from={0}&to={1}&query={2}&transtype=tran";

			url = MessageFormat.format(url, from_lang,target_lang,URLEncoder.encode(text, ENCODING));

			is = (InputStream) HttpClientUtil.download(url, null, null, true);
			byte b[] = new byte[1024];
			int i = 0;
			String result = "";
			while ((i = is.read(b)) != -1) {
				result += new String(b, 0, i);
			}
			return result;
		} finally {
			IOUtils.closeQuietly(is);
			is = null;
		}
	}

}
