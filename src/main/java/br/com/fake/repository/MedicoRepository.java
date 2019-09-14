/**
 * 
 */
package br.com.fake.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fake.model.Medico;

/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {


}
