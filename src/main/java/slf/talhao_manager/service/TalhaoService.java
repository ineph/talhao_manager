package slf.talhao_manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import slf.talhao_manager.exception.CustomException;
import slf.talhao_manager.model.TalhaoEntity;
import slf.talhao_manager.dto.TalhaoDTO;
import slf.talhao_manager.repository.TalhaoJdbcTemplateRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TalhaoService {
    private final TalhaoJdbcTemplateRepository talhaoRepo;


    public Long salvarTalhao(TalhaoDTO novoTalhao){
        TalhaoEntity talhao = new TalhaoEntity();

        if (novoTalhao.getGeom().getFeatures().size() > 1) {
            throw new CustomException("Deve ser cadastrado apenas um polígono por vez para cada fazenda!", HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            List coord = novoTalhao.getGeom().getFeatures().get(0).getGeometry().getCoordinates();
            Long id_criado = talhaoRepo.inserirPoligono(novoTalhao.getCdIdFazenda(), coordConversor(coord));
            return id_criado;
        }

    }

    public String coordConversor(List<List<List<Double>>> listCoord){
        StringBuilder novaString = new StringBuilder("POLYGON(");

        for (List<List<Double>> dupla : listCoord){
            novaString.append("(");
            for (List<Double> ponto : dupla) {
                double longitude = ponto.get(0);
                double latitude = ponto.get(1);
                novaString.append(longitude).append(" ").append(latitude).append(",");
            }

            novaString.deleteCharAt(novaString.length() -1);
            novaString.append("),");
        }
        novaString.deleteCharAt(novaString.length() -1);
        novaString.append(")");

        return novaString.toString();
    }

    public List<List<List<Double>>> polygonConversor(String poligono){
        List<List<List<Double>>> coordenadas = new ArrayList<>();
        poligono = poligono.trim().replace("POLYGON((", "").replace("))", "");
        String[] duplas = poligono.split("\\),\\(");

        for (String dupla : duplas) {
            List<List<Double>> ringCoordinates = new ArrayList<>();
            String[] coords = dupla.split(",");
            for (String coordenada : coords) {
                String[] parts = coordenada.trim().split(" ");
                if (parts.length == 2) {
                    double longitude = Double.parseDouble(parts[0]);
                    double latitude = Double.parseDouble(parts[1]);
                    List<Double> point = new ArrayList<>();
                    point.add(longitude);
                    point.add(latitude);
                    ringCoordinates.add(point);
                }
            }
            coordenadas.add(ringCoordinates);
        }
        return coordenadas;
    }
}
