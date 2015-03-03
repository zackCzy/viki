package Utils;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.util.Streams;
/**
 * UEditor文件上传辅助类
 *
 */
public class Uploader {
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private long size = 0;
	private String timeSting;
	private FileInputStream fs = null;
	private String title = "";
	private HttpServletRequest request;
	// 保存路径
	private String savePath = "upload";
	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip", ".pdf",".txt", ".swf", ".wmv", ".gif", ".png", ".jpg", ".jpeg", ".bmp" };
	// 文件大小限制，单位KB
	private int maxSize = 10000;
	
	private HashMap<String, String> errorInfo = new HashMap<String, String>();

	public Uploader(File fs,HttpServletRequest request) throws FileNotFoundException {
		this.fs = new FileInputStream(fs);
		this.request=request;
		HashMap<String, String> tmp = this.errorInfo;
		tmp.put("SUCCESS", "SUCCESS"); //默认成功
		tmp.put("NOFILE", "未包含文件上传域");
		tmp.put("TYPE", "不允许的文件格式");
		tmp.put("SIZE", "文件大小超出限制");
		tmp.put("ENTYPE", "请求类型ENTYPE错误");
		tmp.put("REQUEST", "上传请求异常");
		tmp.put("IO", "IO异常");
		tmp.put("DIR", "目录创建失败");
		tmp.put("UNKNOWN", "未知错误");
		
	}

	public void upload(){
		String savePath = this.getFolder(this.savePath);
		FileOutputStream dff=null;
		try {
			this.originalName =new Date().getTime()+UUID.randomUUID().toString();
			this.fileName = this.originalName;
			this.type = "png";
			this.url = "upload/"+this.timeSting + "/" + this.fileName+".png";
			File file=new File(savePath + "/" + this.fileName+".png");
			dff= new FileOutputStream(file);
			BufferedOutputStream output = new BufferedOutputStream(dff);
			Streams.copy(fs, output, true);
			this.state=this.errorInfo.get("SUCCESS");
			this.size = file.length();
			
//			while ((fs.read(b))!=-1&& (n > 0)) {
//				dff.write(b);
//				if (!fis.isFormField()) {
//					this.originalName = fis.getName().substring(fis.getName().lastIndexOf(System.getProperty("file.separator")) + 1);
//					if (!this.checkFileType(this.originalName)) {
//						this.state = this.errorInfo.get("TYPE");
//						continue;
//					}
//					this.fileName = this.getName(this.originalName);
//					this.type = this.getFileExt(this.fileName);
//					this.url = savePath + "/" + this.fileName;
//					BufferedInputStream in = new BufferedInputStream(fis.openStream());
//					File file = new File(this.getPhysicalPath(this.url));
//					FileOutputStream out = new FileOutputStream( file );
//					BufferedOutputStream output = new BufferedOutputStream(out);
//					Streams.copy(in, output, true);
//					this.state=this.errorInfo.get("SUCCESS");
//					this.size = file.length();
//					//UE中只会处理单张上传，完成后即退出
//					break;
//				} else {
//					String fname = fis.getFieldName();
//					//只处理title，其余表单请自行处理
//					if(!fname.equals("pictitle")){
//						continue;
//					}
//                    BufferedInputStream in = new BufferedInputStream(fis.openStream());
//                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                    StringBuffer result = new StringBuffer();  
//                    while (reader.ready()) {  
//                        result.append((char)reader.read());  
//                    }
//                    this.title = new String(result.toString().getBytes(),"utf-8");
//                    reader.close();  
//				}
//}
		} catch (Exception e) {
			this.state = this.errorInfo.get("UNKNOWN");
		}finally{
			if(dff!=null){
				try {
					dff.close();
				} catch (IOException e) {
					System.out.println("输出流关闭失败");
				}
			}
		}
		
	}
	
//	/**
//	 * 接受并保存以base64格式上传的文件
//	 * @param fieldName
//	 */
//	public void uploadBase64(String fieldName){
//		String savePath = this.getFolder(this.savePath);
//		String base64Data = this.request.getParameter(fieldName);
//		this.fileName = this.getName("test.png");
//		this.url = savePath + "/" + this.fileName;
//		BASE64Decoder decoder = new BASE64Decoder();
//		try {
//			File outFile = new File(this.getPhysicalPath(this.url));
//			OutputStream ro = new FileOutputStream(outFile);
//			byte[] b = decoder.decodeBuffer(base64Data);
//			for (int i = 0; i < b.length; ++i) {
//				if (b[i] < 0) {
//					b[i] += 256;
//				}
//			}
//			ro.write(b);
//			ro.flush();
//			ro.close();
//			this.state=this.errorInfo.get("SUCCESS");
//		} catch (Exception e) {
//			this.state = this.errorInfo.get("IO");
//		}
//	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles).iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 依据原始文件名生成新文件名
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis() + this.getFileExt(fileName);
	}

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * @param path 
	 * @return 
	 */
	private String getFolder(String path) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		this.timeSting=formater.format(new Date());
		path += "/" + timeSting;
		path=this.getPhysicalPath(path);
		File dir = new File(path);
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.state = this.errorInfo.get("DIR");
				return "";
			}
		}
		return path;
	}

	/**
	 * 根据传入的虚拟路径获取物理路径
	 * 
	 * @param path
	 * @return
	 */
	private String getPhysicalPath(String path) {
		//String servletPath = this.request.getServletPath();	
		URL realPath=Uploader.class.getClassLoader().getResource("../../upload");
		return new File(realPath.getFile()).getParent() +"/" +path;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(int size) {
		this.maxSize = size;
	}

	public long getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}

	public String getState() {
		return this.state;
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}
}
