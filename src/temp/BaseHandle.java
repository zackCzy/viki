package temp;
//package cn.mini.dao.refactor;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//
//import Utils.DBManager;
//import cn.mini.exception.DaoExcption;
//
//public  class BaseHandle {
//
//	protected int update(String sql,Object ...args) {	
//		Connection c =DBManager.getConn();
//		PreparedStatement pt = null;
//		ResultSet rt = null;
//		try {
//			pt = c.prepareStatement(sql);
//			for (int i = 0; i < args.length; i++) {
//				pt.setObject(i+1, args[i]);
//			}
//			return pt.executeUpdate();	
//		} catch (Exception e) {
//			throw new DaoExcption(e.getMessage(), e);
//		} finally {
//			DBManager.closeBase(c, pt, rt);
//		}
//	}
//	protected Object query(String sql,RowMapper rm,Object ...args) {	
//		Connection c =DBManager.getConn();
//		PreparedStatement pt = null;
//		ResultSet rt = null;
//		try {
//			pt = c.prepareStatement(sql);
//			for (int i = 0; i < args.length; i++) {
//				pt.setObject(i+1, args[i]);
//			}		
//			rt=pt.executeQuery();
//			Object obj=null;
//			while(rt.next()){
//				 obj=rm.rowMapper(rt);
//			}
//			return obj;
//		} catch (Exception e) {
//			throw new DaoExcption(e.getMessage(), e);
//		} finally {
//			DBManager.closeBase(c, pt, rt);
//		}
//	}
//	
//}
