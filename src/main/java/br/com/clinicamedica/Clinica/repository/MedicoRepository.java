package br.com.clinicamedica.Clinica.repository;

import br.com.clinicamedica.Clinica.model.MedicoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoModel, Long> {
}
