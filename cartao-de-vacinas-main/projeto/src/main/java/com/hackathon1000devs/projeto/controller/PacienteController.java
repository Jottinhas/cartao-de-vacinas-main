package com.hackathon1000devs.projeto.controller;

import com.hackathon1000devs.projeto.model.PacienteModel;
import com.hackathon1000devs.projeto.repository.PacienteRepository;
import com.hackathon1000devs.projeto.repository.ImunizacaoRepository; // Adicione este import
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; // Adicione este import
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private ImunizacaoRepository imunizacaoRepository;

    @Transactional // Garante que se um erro ocorrer, nada será excluído
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        try {
            if (repository.existsById(id)) {

                imunizacaoRepository.deleteByPacienteId(id);

                repository.deleteById(id);

                return ResponseEntity.ok("Paciente e todo o seu histórico foram excluídos com sucesso!");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Paciente não encontrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao processar exclusão: " + e.getMessage());
        }
    }
}