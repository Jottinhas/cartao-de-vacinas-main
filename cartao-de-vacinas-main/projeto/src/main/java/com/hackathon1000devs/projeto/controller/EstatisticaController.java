package com.hackathon1000devs.projeto.controller;

import com.hackathon1000devs.projeto.repository.ImunizacaoRepository;
import com.hackathon1000devs.projeto.repository.VacinaRepository;
import com.hackathon1000devs.projeto.service.EstatisticaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatisticas")
public class EstatisticaController {

    @Autowired
    private EstatisticaService service;

    @Autowired
    private ImunizacaoRepository imunizacaoRepo;

    @Autowired
    private VacinaRepository vacinaRepo;

    @GetMapping("/imunizacoes/paciente/{id}")
    public ResponseEntity<?> qtdeAplicadas(@PathVariable Long id) {
        return ResponseEntity.ok(imunizacaoRepo.countByPacienteId(id));
    }

    @GetMapping("/imunizacoes_atrasadas/paciente/{id}")
    public ResponseEntity<?> qtdeAtrasadas(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.calcularVacinasAtrasadas(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/proximas_imunizacoes/paciente/{id}")
    public ResponseEntity<?> qtdeProximas(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.calcularProximasVacinas(id));
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Erro: " + e.getMessage());
        }
    }

    @GetMapping("/imunizacoes/idade_maior/{meses}")
    public ResponseEntity<?> acimaIdade(@PathVariable Integer meses) {
        return ResponseEntity.ok(vacinaRepo.countVacinasAcimaIdade(meses));
    }
}