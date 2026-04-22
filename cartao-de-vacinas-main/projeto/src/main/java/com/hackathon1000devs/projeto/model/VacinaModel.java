package com.hackathon1000devs.projeto.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "vacina")
@Data
public class VacinaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_vacina; // Mudei para Integer para casar com seu script SQL

    private String nome_vacina;
    private String descricao_vacina;
    private Integer limite_aplicacao;
    @Enumerated(EnumType.STRING) // IMPORTANTE: Grava o texto 'CRIANÇA' no banco
    private PublicoAlvo publico_alvo; // CRIANÇA, ADOLESCENTE, ADULTO, GESTANTE
}

