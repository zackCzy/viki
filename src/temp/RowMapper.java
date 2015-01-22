package temp;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper {
	 public Object rowMapper(ResultSet rs)throws SQLException;
}
