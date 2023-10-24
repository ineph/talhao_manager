package slf.talhao_manager.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;


@Repository
@RequiredArgsConstructor
public class TalhaoJdbcTemplateRepository {
    private final JdbcTemplate jdbcTemplate;


    public Long inserirPoligono(long cd_id_fazenda, String coord){
            String sql = "INSERT INTO cdt_field (cd_id_fazenda, geom) VALUES (?, ST_GeomFromText(?, 4326)) RETURNING cd_id";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setLong(1, cd_id_fazenda);
                    ps.setString(2, coord);
                    return ps;
                },
                keyHolder
            );
            return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }
}
