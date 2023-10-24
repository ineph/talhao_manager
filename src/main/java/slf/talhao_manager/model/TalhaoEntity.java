package slf.talhao_manager.model;

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer;
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.hibernate.annotations.Formula;
import org.locationtech.jts.geom.Geometry;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "cdt_field" )
public class TalhaoEntity implements Serializable {

    @Id
    @Column(name = "cd_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cdId;

    @Column(name = "cd_id_fazenda")
    private long cdIdFazenda;

    @Column(columnDefinition = "geometry(Geometry,4326)", name="geom")
    @JsonSerialize(using = GeometrySerializer.class)
    @JsonDeserialize(using = GeometryDeserializer.class)
    @Formula("ST_AsText(geom)")
    private Geometry geom;
}
