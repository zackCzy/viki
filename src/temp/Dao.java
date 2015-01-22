package temp;




public interface Dao {
	public int insert(Object bean);
	public int update(Object bean);
	public int remove(String ...key);
	public Object select(String ...key);
	public Object handleDao(Handle hd);
}
