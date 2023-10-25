package slf.talhao_manager.dto;

import lombok.Data;

@Data
public class FeatureDTO {

    private final String type = "Feature";
    private PropertiesDTO properties;
    private GeometryDTO geometry;
}
