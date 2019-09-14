package br.com.fake.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.fake.model.Paciente;
import br.com.fake.repository.PacienteRepository;

@Service
public class PacienteService {
	
	@Autowired
	private PacienteRepository repository;
	
	@Transactional(readOnly=true)
	public List<Paciente> lista(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void salva(Paciente paciente) {
		this.repository.save(paciente);
	}

}
