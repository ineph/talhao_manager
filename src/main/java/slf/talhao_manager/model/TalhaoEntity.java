package slf.talhao_manager.model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;

@Data
@Entity(name = "cdt_field" )
public class TalhaoEntity {

    @Id
    @Column(name = "cd_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cd_id;

    @Column(name = "cd_id_fazenda")
    private long cd_id_fazenda;

    @Column(columnDefinition = "geometry(Geometry,4326)")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    private Geometry geom;
}
