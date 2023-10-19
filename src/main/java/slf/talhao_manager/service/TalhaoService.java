package slf.talhao_manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import slf.talhao_manager.model.TalhaoEntity;
import slf.talhao_manager.dto.TalhaoDTO;
import slf.talhao_manager.repository.TalhaoRepository;

import java.util.List;

@Service
public class TalhaoService {
    private final TalhaoRepository talhaoRepo;

    @Autowired
    public TalhaoService(TalhaoRepository talhaoRepo){
        this.talhaoRepo = talhaoRepo;
    }

    public Long salvarTalhao(TalhaoDTO novoTalhao){
        TalhaoEntity talhao = new TalhaoEntity();

        if (novoTalhao.getGeom().getFeatures().size() > 1) {
            System.out.println("deveria dar erro, pedindo para cadastrar apenas um polígono/talhão por vez, para cada fazenda ;)");
            return -1L;
        } else {
            List coord = novoTalhao.getGeom().getFeatures().get(0).getGeometry().getCoordinates();
            Long id_criado = talhaoRepo.inserirPoligono(novoTalhao.getCd_id_fazenda(), coondConverter(coord));
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
