package com.hackathon1000devs.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dose")
@Data
public class DoseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_dose;
    @ManyToOne
    @JoinColumn(name = "id_vacina")
    private VacinaModel  vacina;
    private String descricao_dose;
    private Integer idade_recomendada_aplicacao;
}
