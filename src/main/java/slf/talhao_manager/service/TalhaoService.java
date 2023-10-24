package slf.talhao_manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import slf.talhao_manager.exception.CustomException;
import slf.talhao_manager.model.TalhaoEntity;
import slf.talhao_manager.dto.TalhaoDTO;
import slf.talhao_manager.repository.TalhaoJdbcTemplateRepository;

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
            Long id_criado = talhaoRepo.inserirPoligono(novoTalhao.getCdIdFazenda(), coondConverter(coord));
            return id_criado;
        }

    }

    public String coondConverter(List<List<List<Double>>> listCoord){
        StringBuilder novaString = new StringBuilder("POLYGON(");

        for (List<List<Double>> anel : listCoord){
            novaString.append("(");
            for (List<Double> ponto : anel) {
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
}
