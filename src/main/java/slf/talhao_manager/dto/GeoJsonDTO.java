package slf.talhao_manager.dto;

import lombok.Data;
import java.util.List;

@Data
public class GeoJsonDTO {

    private final String type = "FeatureCollection";
    private List<FeatureDTO> features;
}
