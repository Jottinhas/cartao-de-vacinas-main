package com.hackathon1000devs.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "imunizacoes")
@Data
public class ImunizacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_imunizacao;
    @ManyToOne
    @JoinColumn(name = "id_paciente")
    private PacienteModel paciente;
    @ManyToOne @JoinColumn(name = "id_dose")
    private DoseModel dose;
    private LocalDate data_aplicacao;
    private String fabricante;
    private String lote;
    private String local_aplicacao;
    private String profissional_aplicador;
}
