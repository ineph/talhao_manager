package slf.talhao_manager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import slf.talhao_manager.dto.TalhaoDTO;
import slf.talhao_manager.exception.CustomException;
import slf.talhao_manager.model.TalhaoEntity;
import slf.talhao_manager.repository.TalhaoJpaRepository;
import slf.talhao_manager.service.TalhaoService;

import java.util.List;

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
    public ResponseEntity<List<TalhaoEntity>> getTalhaoPorFazenda(@PathVariable(value = "cdIdFazenda") Long cdIdFazenda) {
        List<TalhaoEntity> talhaoEntity = talhaoRepo.findByCdIdFazenda(cdIdFazenda);

        if (talhaoEntity.isEmpty()) {
            new CustomException("Nenhum Talhão foi encontrado para a Fazenda " + cdIdFazenda, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.status(HttpStatus.OK).body(talhaoEntity);
    }
}
