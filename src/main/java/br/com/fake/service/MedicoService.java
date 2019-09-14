/**
 * 
 */
package br.com.fake.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fake.model.Medico;
import br.com.fake.repository.MedicoRepository;

/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Service
public class MedicoService {

	@Autowired
	private MedicoRepository repository;

	@Transactional(readOnly=true)
	public List<Medico> lista(){
		return this.repository.findAll();
	}

	@Transactional
	public void salva(Medico medico) {
		this.repository.save(medico);
	}

	@Transactional
	public void delete(long id) {
		this.repository.deleteById(id);		
	}

	@Transactional
	public void edit(long id, Medico medico) {	
		Optional<Medico> medicoEditar = this.repository.findById(id);
		
		if(medicoEditar.isPresent()) {
			medico.setId(medicoEditar.get().getId());
		}

		this.repository.save(medico);		
	}
	
	@Transactional
	public Optional<Medico> getMedicoByID (long id) {		
		return this.repository.findById(id);
	}

}
