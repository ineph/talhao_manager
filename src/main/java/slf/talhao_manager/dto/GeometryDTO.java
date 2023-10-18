package slf.talhao_manager.dto;

import lombok.Data;

import java.util.List;

@Data
public class GeometryDTO {
    private String type;
    private List<List<List<Double>>> coordinates;
}
