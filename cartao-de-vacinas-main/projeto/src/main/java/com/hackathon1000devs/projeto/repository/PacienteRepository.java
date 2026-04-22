package com.hackathon1000devs.projeto.repository;


import com.hackathon1000devs.projeto.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
    // O JpaRepository já fornece os métodos:
    // save(), findById(), findAll(), deleteById(), etc.
    // O JpaRepository já entrega o findAll, findById, save e deleteById por padrão.
}
