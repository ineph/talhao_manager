package slf.talhao_manager.dto;

import lombok.Data;
import java.util.List;

@Data
public class GeoJsonDTO {
    private String type;
    private List<FeatureDTO> features;
}
