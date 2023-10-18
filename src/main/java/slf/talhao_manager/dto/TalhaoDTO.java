package slf.talhao_manager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class TalhaoDTO {

    private long cd_id_fazenda;

    private GeoJsonDTO geom;
}
