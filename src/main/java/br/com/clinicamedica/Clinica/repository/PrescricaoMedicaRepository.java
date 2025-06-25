package br.com.clinicamedica.Clinica.repository;

import br.com.clinicamedica.Clinica.model.PrescricaoMedicaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescricaoMedicaRepository extends JpaRepository<PrescricaoMedicaModel, Long> {
}
