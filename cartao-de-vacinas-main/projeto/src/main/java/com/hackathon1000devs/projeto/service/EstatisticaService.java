package com.hackathon1000devs.projeto.service;

import com.hackathon1000devs.projeto.model.PacienteModel;
import com.hackathon1000devs.projeto.repository.DoseRepository;
import com.hackathon1000devs.projeto.repository.ImunizacaoRepository;
import com.hackathon1000devs.projeto.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class EstatisticaService {

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private DoseRepository doseRepo;

    @Autowired
    private ImunizacaoRepository imunizacaoRepo;

    public long calcularVacinasAtrasadas(Long pacienteId) {
        PacienteModel paciente = pacienteRepo.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        // Calcula a idade total em meses (ex: 2 anos e 3 meses = 27 meses)
        long idadeEmMeses = ChronoUnit.MONTHS.between(paciente.getData_nascimento(), LocalDate.now());

        // Doses que o calendário recomenda até a idade atual dele
        long dosesDeveriaTerTomado = doseRepo.countByIdadeRecomendadaAté(idadeEmMeses);

        // Doses que ele realmente registrou no sistema
        long dosesTomadas = imunizacaoRepo.countByPacienteId(pacienteId);

        // Atrasadas = Recomendadas - Aplicadas
        long atrasadas = dosesDeveriaTerTomado - dosesTomadas;

        return Math.max(0, atrasadas); // Retorna 0 se ele estiver em dia ou adiantado
    }

    public long calcularProximasVacinas(Long pacienteId) {
        PacienteModel paciente = pacienteRepo.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        long idadeEmMeses = ChronoUnit.MONTHS.between(paciente.getData_nascimento(), LocalDate.now());

        // Busca doses recomendadas para o mês que vem (Idade Atual + 1)
        return doseRepo.countByIdadeRecomendadaExata(idadeEmMeses + 1);
    }
}