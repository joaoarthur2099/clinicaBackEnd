package br.com.clinicamedica.Clinica.repository;

import br.com.clinicamedica.Clinica.model.ConsultaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<ConsultaModel, Long> {
}
