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

    public void salvarTalhao(TalhaoDTO novoTalhao){
        TalhaoEntity talhao = new TalhaoEntity();

        if (novoTalhao.getGeom().getFeatures().size() > 1) {
            System.out.println("deveria dar erro, pedindo para cadastrar apenas um polígono/talhão por vez, para cada fazenda ;)");
        } else {
            List coord = novoTalhao.getGeom().getFeatures().get(0).getGeometry().getCoordinates();
            talhaoRepo.inserirPoligono(novoTalhao.getCd_id_fazenda(), coondConverter(coord));
        }

    }

    public String coondConverter(List<List<Double>> listCoord){
        StringBuilder novaString = new StringBuilder("POLYGON((");

        for (List<Double> coordenadas : listCoord){
            if (coordenadas.size() != 2){
                throw new IllegalArgumentException("Cada cordenada deve conter um registro de latitude e um de longitude, nem mais nem menos!");
            }
            double longitude = coordenadas.get(0);
            double latitude = coordenadas.get(1);
            novaString.append(longitude).append(" ").append(latitude).append(",");
        }
        novaString.deleteCharAt(novaString.length() -1);
        novaString.append("))");
        return novaString.toString();
    }
}
