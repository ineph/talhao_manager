package slf.talhao_manager.repository;

import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import slf.talhao_manager.model.TalhaoEntity;


@Repository
public class TalhaoRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TalhaoRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public void inserirPoligono(long cd_id_fazenda, String coord){
        try {
            Geometry geometry = new WKTReader().read(coord);
            String sql = "INSERT INTO cdt_field (cd_id_fazenda, geom) VALUES (?, ST_GeomFromText(?, 4326))";
            jdbcTemplate.update(sql, cd_id_fazenda, geometry.toText());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
