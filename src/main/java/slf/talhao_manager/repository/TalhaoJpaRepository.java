package slf.talhao_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import slf.talhao_manager.dto.TalhaoOutput;
import slf.talhao_manager.model.TalhaoEntity;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Repository
public interface TalhaoJpaRepository extends JpaRepository<TalhaoEntity, Long> {

    @Query(value = "SELECT f.cd_id as cdId, f.cd_id_fazenda as cdIdFazenda, ST_AsText(f.geom) as geom FROM cdt_field f WHERE f.cd_id_fazenda = :cdIdFazenda", nativeQuery = true)
    List<TalhaoOutput> findAllByCdIdFazenda(@Param("cdIdFazenda") Long cdIdFazenda);

//    List<TalhaoEntity> findAllByCdIdFazenda(Long fazendaId);
    Optional<TalhaoEntity> findByCdId(Long cd_id);
}
