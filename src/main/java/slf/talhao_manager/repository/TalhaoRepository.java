package slf.talhao_manager.repository;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import slf.talhao_manager.model.TalhaoEntity;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;


@Repository
public class TalhaoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TalhaoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

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
