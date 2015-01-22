package temp;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public interface Handle {
    public Object handleDao(NamedParameterJdbcTemplate jdbc);
}
