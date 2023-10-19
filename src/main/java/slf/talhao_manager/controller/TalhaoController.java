package slf.talhao_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import slf.talhao_manager.model.TalhaoEntity;
import slf.talhao_manager.dto.TalhaoDTO;
import slf.talhao_manager.service.TalhaoService;

@RestController
@RequestMapping("cadastro")
public class TalhaoController {

    private final TalhaoService talhaoSvc;

    @Autowired
    public TalhaoController(TalhaoService talhaoSvc) {
        this.talhaoSvc = talhaoSvc;
    }

    @PostMapping
    public ResponseEntity<String> cadastrar(@RequestBody TalhaoDTO novoTalhao){

        Long id_talhao = talhaoSvc.salvarTalhao(novoTalhao);
        return ResponseEntity.status(HttpStatus.CREATED).body("Talhão criado! ID: " + id_talhao);
    }
}
