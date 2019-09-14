package br.com.fake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fake.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>  {

}
