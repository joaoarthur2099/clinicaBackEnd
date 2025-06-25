package br.com.clinicamedica.Clinica.repository;

import br.com.clinicamedica.Clinica.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {
    UserModel findByUserNameEquals(String userName);
}
