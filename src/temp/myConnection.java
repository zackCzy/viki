package temp;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;


class myConnection implements Connection {
	private Connection realConnection;
	myConnection (Connection c){
		this.realConnection=c;
	}
	@Override
	public void close() throws SQLException {
		if(jdbc_Util.list.size()<=0||jdbc_Util.nowCount<jdbc_Util.baseCount)
			jdbc_Util.addConnection(this);
		else{
			this.realConnection.close();
		    jdbc_Util.nowCount--;
		}
	}
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {		
		return this.realConnection.unwrap(iface);
	}
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return this.isWrapperFor(iface);
	}
	@Override
	public Statement createStatement() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createStatement();
	}
	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareStatement(sql);
	}
	@Override
	public CallableStatement prepareCall(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareCall(sql);
	}
	@Override
	public String nativeSQL(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.nativeSQL(sql);
	}
	@Override
	public void setAutoCommit(boolean autoCommit) throws SQLException {
		// TODO Auto-generated method stub
		this.realConnection.setAutoCommit(autoCommit);
	}
	@Override
	public boolean getAutoCommit() throws SQLException {
		// TODO Auto-generated method stub
		return this.getAutoCommit();
	}
	@Override
	public void commit() throws SQLException {
		// TODO Auto-generated method stub
		this.realConnection.commit();
	}
	@Override
	public void rollback() throws SQLException {
		// TODO Auto-generated method stub
		this.realConnection.rollback();
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.isClosed();
	}
	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getMetaData();
	}
	@Override
	public void setReadOnly(boolean readOnly) throws SQLException {
		// TODO Auto-generated method stub
		this.setReadOnly(readOnly);
	}
	@Override
	public boolean isReadOnly() throws SQLException {
		// TODO Auto-generated method stub
		return this.isReadOnly();
	}
	@Override
	public void setCatalog(String catalog) throws SQLException {
		// TODO Auto-generated method stub
		this.setCatalog(catalog);
	}
	@Override
	public String getCatalog() throws SQLException {
		// TODO Auto-generated method stub
		return this.getCatalog();
	}
	@Override
	public void setTransactionIsolation(int level) throws SQLException {
		// TODO Auto-generated method stub
		this.realConnection.setTransactionIsolation(level);
	}
	@Override
	public int getTransactionIsolation() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getTransactionIsolation();
	}
	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getWarnings();
	}
	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub
		this.realConnection.clearWarnings();
	}
	@Override
	public Statement createStatement(int resultSetType, int resultSetConcurrency)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createStatement(resultSetType, resultSetConcurrency);
	}
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency);
	}
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareCall(sql, resultSetType, resultSetConcurrency);
	}
	@Override
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getTypeMap();
	}
	@Override
	public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
		// TODO Auto-generated method stub
		this.realConnection.setTypeMap(map);
	}
	@Override
	public void setHoldability(int holdability) throws SQLException {
		// TODO Auto-generated method stub
		this.setHoldability(holdability);
	}
	@Override
	public int getHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getHoldability();
	}
	@Override
	public Savepoint setSavepoint() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.setSavepoint();
	}
	@Override
	public Savepoint setSavepoint(String name) throws SQLException {
		// TODO Auto-generated method stub
		return setSavepoint(name);
	}
	@Override
	public void rollback(Savepoint savepoint) throws SQLException {
		// TODO Auto-generated method stub
		this.rollback(savepoint);
	}
	@Override
	public void releaseSavepoint(Savepoint savepoint) throws SQLException {
		// TODO Auto-generated method stub
		this.releaseSavepoint(savepoint);
	}
	@Override
	public Statement createStatement(int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createStatement(resultSetType, resultSetConcurrency);
	}
	@Override
	public PreparedStatement prepareStatement(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}
	@Override
	public CallableStatement prepareCall(String sql, int resultSetType,
			int resultSetConcurrency, int resultSetHoldability)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
	}
	@Override
	public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareStatement(sql, autoGeneratedKeys);
	}
	@Override
	public PreparedStatement prepareStatement(String sql, int[] columnIndexes)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareStatement(sql, columnIndexes);
	}
	@Override
	public PreparedStatement prepareStatement(String sql, String[] columnNames)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.prepareStatement(sql, columnNames);
	}
	@Override
	public Clob createClob() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createClob();
	}
	@Override
	public Blob createBlob() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createBlob();
	}
	@Override
	public NClob createNClob() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createNClob();
	}
	@Override
	public SQLXML createSQLXML() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createSQLXML();
	}
	@Override
	public boolean isValid(int timeout) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.isValid(timeout);
	}
	@Override
	public void setClientInfo(String name, String value)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		this.realConnection.setClientInfo(name, value);
	}
	@Override
	public void setClientInfo(Properties properties)
			throws SQLClientInfoException {
		// TODO Auto-generated method stub
		this.realConnection.setClientInfo(properties);
	}
	@Override
	public String getClientInfo(String name) throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getClientInfo(name);
	}
	@Override
	public Properties getClientInfo() throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.getClientInfo();
	}
	@Override
	public Array createArrayOf(String typeName, Object[] elements)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createArrayOf(typeName, elements);
	}
	@Override
	public Struct createStruct(String typeName, Object[] attributes)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.realConnection.createStruct(typeName, attributes);
	}
	@Override
	public void setSchema(String schema) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getSchema() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setNetworkTimeout(Executor executor, int milliseconds)
			throws SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getNetworkTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
}
