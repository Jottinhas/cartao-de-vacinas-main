package com.hackathon1000devs.projeto.repository;

import com.hackathon1000devs.projeto.model.VacinaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VacinaRepository extends JpaRepository<VacinaModel, Long> {

    @Query(value = "SELECT * FROM vacina WHERE publico_alvo = :faixa", nativeQuery = true)
    List<VacinaModel> findByFaixaEtaria(@Param("faixa") String faixa);

    @Query(value = "SELECT DISTINCT v.* FROM vacina v " +
            "INNER JOIN dose d ON v.id_vacina = d.id_vacina " +
            "WHERE d.idade_recomendada_aplicacao > :meses", nativeQuery = true)
    List<VacinaModel> findVacinasAcimaIdade(@Param("meses") int meses);

    @Query(value = "SELECT COUNT(DISTINCT v.id_vacina) FROM vacina v " +
            "INNER JOIN dose d ON v.id_vacina = d.id_vacina " +
            "WHERE d.idade_recomendada_aplicacao > :meses", nativeQuery = true)
    long countVacinasAcimaIdade(@Param("meses") int meses);
}