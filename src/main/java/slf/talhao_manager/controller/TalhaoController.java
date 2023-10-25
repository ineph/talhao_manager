package slf.talhao_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slf.talhao_manager.dto.GeoJsonDTO;
import slf.talhao_manager.dto.TalhaoDTO;
import slf.talhao_manager.repository.TalhaoJpaRepository;
import slf.talhao_manager.service.TalhaoService;


@RestController
public class TalhaoController {

    private final TalhaoService talhaoSvc;
    private final TalhaoJpaRepository talhaoRepo;

    public TalhaoController(TalhaoService talhaoSvc, TalhaoJpaRepository talhaoRepo) {
        this.talhaoSvc = talhaoSvc;
        this.talhaoRepo = talhaoRepo;
    }

    @PostMapping("cadastro")
    public ResponseEntity<String> postTalhao(@RequestBody TalhaoDTO novoTalhao){

        Long id_talhao = talhaoSvc.salvarTalhao(novoTalhao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Talhão criado! ID: " + id_talhao);
    }

    @GetMapping("talhao/fazenda/{cdIdFazenda}")
    public ResponseEntity<GeoJsonDTO> getTalhaoPorFazenda(@PathVariable(value = "cdIdFazenda") Long cdIdFazenda) {
        return ResponseEntity.status(HttpStatus.OK).body(talhaoSvc.findTalhoesByCdIdFazenda(cdIdFazenda));
    }
}
