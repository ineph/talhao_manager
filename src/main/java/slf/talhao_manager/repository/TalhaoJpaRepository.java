package slf.talhao_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import slf.talhao_manager.model.TalhaoEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface TalhaoJpaRepository extends JpaRepository<TalhaoEntity, Long> {
    List<TalhaoEntity> findByCdIdFazenda(Long cd_id_fazenda);
    Optional<TalhaoEntity> findByCdId(Long cd_id);
}
