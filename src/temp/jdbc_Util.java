package temp;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public final class jdbc_Util {
	private jdbc_Util() {
	};
	private static String url, name, pass;
	static int baseCount =5;
	static int maxCount =50;
	static int nowCount = 0;
	static LinkedList<Connection> list = new LinkedList<Connection>();
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Properties p = new Properties();
			InputStream in = jdbc_Util.class.getClassLoader()
					.getResourceAsStream("../config/db.properties");
			p.load(in);
			url = p.getProperty("url");
			name = p.getProperty("name");
			pass = p.getProperty("password");
			p=null;
			for (int i = 0; i <baseCount; i++) {				
				list.addLast(getMyConnection());
				nowCount++;
			}
			in.close();
			in=null;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("ע����ʧ��");
		} catch (IOException e) {
			throw new ExceptionInInitializerError("��ȡ�����ļ�����");
		} catch (SQLException e) {
			throw new RuntimeException("�˺��������");
		}
	}
 
	public static Connection getConnection() {
		synchronized (list) {
			if (list.size() <=0) {
				try {
					if (nowCount < maxCount) {
						nowCount++;
						addConnection(getMyConnection());
					} else {
						while(list.size()<= 0){
							Thread.sleep(1000);
						}
					}
				} catch (Exception e) {
				}
			}
			return list.removeFirst();
		}
	}
    private static Connection getMyConnection() throws SQLException{
		return  new myCon(DriverManager.getConnection(url, name, pass)).bind();
    	//new myConnection(DriverManager.getConnection(url, name, pass));
    }
    static void addConnection(Connection c){
    	list.addLast(c);
    }
	public static void closeBase(Connection c, PreparedStatement pt,
			ResultSet rt) {
		try {
			if (rt != null) {
				rt.close();
			}
		} catch (SQLException e) {
		} finally {
			try {
				if (pt != null) {
					pt.close();
				}
			} catch (SQLException e2) {
			} finally {
				try {
					if (c != null) {
						c.close();
					}
				} catch (SQLException e3) {
				}
			}
		}
	}
	
}

class myCon implements InvocationHandler{
	   private Connection c;
	   private Connection warped;
	   public myCon(Connection realCon){
			this.c=realCon;
		}
		public Connection bind(){
			this.warped=(Connection) Proxy.newProxyInstance(myCon.class.getClassLoader(), new Class[]{Connection.class}, this);
			return warped;
		}

		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {			
			if("close".equals(method.getName())){
				if(jdbc_Util.list.size()<=0||jdbc_Util.nowCount<jdbc_Util.baseCount)
					jdbc_Util.addConnection(this.warped);
				else{
					this.c.close();
				    jdbc_Util.nowCount--;
				}
			}					
			return method.invoke(this.c, args);
		}
	}
