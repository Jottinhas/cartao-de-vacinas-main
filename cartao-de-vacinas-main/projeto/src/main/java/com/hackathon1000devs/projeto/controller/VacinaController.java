package com.hackathon1000devs.projeto.controller;

import com.hackathon1000devs.projeto.model.VacinaModel;
import com.hackathon1000devs.projeto.repository.VacinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacinas")
public class VacinaController {

    @Autowired
    private VacinaRepository repository;

    @GetMapping("/consultar")
    public List<VacinaModel> consultarTodas() {
        return repository.findAll();
    }

    @GetMapping("/consultar/faixa_etaria/{faixa}")
    public ResponseEntity<?> consultarPorFaixa(@PathVariable String faixa) {
        try {
            List<VacinaModel> lista = repository.findByFaixaEtaria(faixa.toUpperCase());
            return ResponseEntity.ok(lista);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao filtrar por faixa etária.");
        }
    }

    // Consultar vacinas recomendadas acima de uma idade
    @GetMapping("/consultar/idade_maior/{meses}")
    public List<VacinaModel> consultarPorIdade(@PathVariable int meses) {
        return repository.findVacinasAcimaIdade(meses);
    }
}