package com.hackathon1000devs.projeto.repository;

import com.hackathon1000devs.projeto.model.ImunizacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ImunizacaoRepository extends JpaRepository<ImunizacaoModel, Long> {

    @Query("SELECT i FROM ImunizacaoModel i WHERE i.paciente.id_paciente = :id")
    List<ImunizacaoModel> findByPacienteId(@Param("id") Long id);

    @Query("SELECT i FROM ImunizacaoModel i WHERE i.paciente.id_paciente = :id AND i.data_aplicacao BETWEEN :inicio AND :fim")
    List<ImunizacaoModel> findByPacienteAndPeriodo(@Param("id") Long id, @Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

    // MUDANÇA AQUI: Usando @Query para não depender da geração automática de nomes do Spring
    @Modifying
    @Query("DELETE FROM ImunizacaoModel i WHERE i.paciente.id_paciente = :id")
    void deleteByPacienteId(@Param("id") Long id);

    @Query("SELECT COUNT(i) FROM ImunizacaoModel i WHERE i.paciente.id_paciente = :id")
    long countByPacienteId(@Param("id") Long id);
}