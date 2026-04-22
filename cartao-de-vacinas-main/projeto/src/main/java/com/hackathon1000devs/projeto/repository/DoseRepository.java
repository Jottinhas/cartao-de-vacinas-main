package com.hackathon1000devs.projeto.repository;

import com.hackathon1000devs.projeto.model.DoseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoseRepository extends JpaRepository<DoseModel, Long> {

    // 1. Conta quantas doses são recomendadas até a idade atual do paciente (para Vacinas Atrasadas)
    @Query("SELECT COUNT(d) FROM DoseModel d WHERE d.idade_recomendada_aplicacao <= :meses")
    long countByIdadeRecomendadaAté(@Param("meses") long meses);

    // 2. Conta quantas doses são recomendadas exatamente para um determinado mês (para Próximas Vacinas)
    @Query("SELECT COUNT(d) FROM DoseModel d WHERE d.idade_recomendada_aplicacao = :meses")
    long countByIdadeRecomendadaExata(@Param("meses") long meses);

    // 3. Busca todas as doses de uma vacina específica (Útil caso precise detalhar o cartão)
    @Query("SELECT d FROM DoseModel d WHERE d.vacina.id_vacina = :idVacina")
    List<DoseModel> findByVacinaId(@Param("idVacina") Long idVacina);
}