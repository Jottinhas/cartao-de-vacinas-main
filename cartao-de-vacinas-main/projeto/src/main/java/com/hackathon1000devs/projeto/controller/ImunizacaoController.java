package com.hackathon1000devs.projeto.controller;

import com.hackathon1000devs.projeto.model.ImunizacaoModel;
import com.hackathon1000devs.projeto.repository.ImunizacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/imunizacao")
public class ImunizacaoController {

    @Autowired
    private ImunizacaoRepository repository;

    @PostMapping("/inserir")
    public ResponseEntity<?> inserir(@RequestBody ImunizacaoModel imunizacao) {
        try {
            ImunizacaoModel salvo = repository.save(imunizacao);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao inserir imunização.");
        }
    }
    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody ImunizacaoModel novosDados) {
        return repository.findById(id).map(imunizacao -> {
            imunizacao.setPaciente(novosDados.getPaciente());
            imunizacao.setDose(novosDados.getDose());
            imunizacao.setData_aplicacao(novosDados.getData_aplicacao());
            imunizacao.setFabricante(novosDados.getFabricante());
            imunizacao.setLote(novosDados.getLote());
            imunizacao.setLocal_aplicacao(novosDados.getLocal_aplicacao());
            imunizacao.setProfissional_aplicador(novosDados.getProfissional_aplicador());
            repository.save(imunizacao);
            return ResponseEntity.ok("Imunização alterada com sucesso!");
        }).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro: Imunização não encontrada."));
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.ok("Imunização excluída com sucesso.");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Imunização não encontrada.");
    }

    @Transactional
    @DeleteMapping("/excluir/paciente/{id_paciente}")
    public ResponseEntity<?> excluirPorPaciente(@PathVariable Long id_paciente) {
        repository.deleteByPacienteId(id_paciente); // Nome novo aqui
        return ResponseEntity.ok("Todas as imunizações do paciente foram excluídas.");
    }

    @GetMapping("/consultar/paciente/{id}")
    public List<ImunizacaoModel> consultarPorPaciente(@PathVariable Long id) {
        return repository.findByPacienteId(id);
    }

    @GetMapping("/consultar/paciente/{id}/aplicacao/{dt_ini}/{dt_fim}")
    public List<ImunizacaoModel> consultarPorPeriodo(
            @PathVariable Long id,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dt_ini,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dt_fim) {
        return repository.findByPacienteAndPeriodo(id, dt_ini, dt_fim);
    }
}