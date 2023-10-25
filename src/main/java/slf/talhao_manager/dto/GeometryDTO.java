package slf.talhao_manager.dto;

import lombok.Data;
import java.util.List;

@Data
public class GeometryDTO {

    private final String type = "Polygon";
    private List<List<List<Double>>> coordinates;
}
