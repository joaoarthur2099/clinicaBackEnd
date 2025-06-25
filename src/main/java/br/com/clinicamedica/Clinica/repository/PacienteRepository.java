package br.com.clinicamedica.Clinica.repository;

import br.com.clinicamedica.Clinica.model.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
}
