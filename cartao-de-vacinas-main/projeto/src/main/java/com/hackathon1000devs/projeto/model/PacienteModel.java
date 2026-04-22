package com.hackathon1000devs.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "paciente")
@Data
public class PacienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_paciente;
    private String nome_paciente;
    private String cpf_paciente;
    @Enumerated(EnumType.STRING)
    private Sexo sexo; // M ou F
    private LocalDate data_nascimento;
}

