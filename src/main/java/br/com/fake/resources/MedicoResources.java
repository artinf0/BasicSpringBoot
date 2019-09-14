package br.com.fake.resources;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fake.model.Medico;
import br.com.fake.service.MedicoService;

@RestController
@RequestMapping("/api")
public class MedicoResources {

	@Autowired
	private MedicoService service;

	@PostMapping("/medicos")	
	public ResponseEntity<?> add(@RequestBody Medico medico) {
		this.service.salva(medico);
		return ResponseEntity.ok(medico);
	}

	@DeleteMapping("/medicos/{id}")
	public ResponseEntity<?> remove (@PathVariable(value = "id") Long id) {			
		Optional<Medico> medicoRepo = this.service.getMedicoByID(id);
		if(medicoRepo.isPresent()) {
			this.service.delete(id);		
			return ResponseEntity.ok().body(medicoRepo);}
		else
			return ResponseEntity.notFound().build();
	}

	@PutMapping("/medicos/{id}")
	public ResponseEntity<?> atualiza(@PathVariable(value = "id") Long id, @Valid @RequestBody Medico medico){
		Optional<Medico> medicoRepo = this.service.getMedicoByID(id);

		medicoRepo.get().setCrm(medico.getCrm());
		medicoRepo.get().setEmail(medico.getEmail());
		medicoRepo.get().setId(id);
		medicoRepo.get().setNome(medico.getNome());

		this.service.salva(medicoRepo.get());

		return ResponseEntity.ok(medico);

	}

	@GetMapping("/medicos")
	public ResponseEntity<List<Medico>> list() {
		List<Medico> medicos = this.service.lista();
		return !medicos.isEmpty() ? ResponseEntity.ok(medicos) : ResponseEntity.noContent().build() ;
	}

	@GetMapping("/medicos/{id}")
	public ResponseEntity<Medico> get(@PathVariable(value = "id") Long id) {
		Optional<Medico> medico = this.service.getMedicoByID(id);	

		return medico.isPresent() ? ResponseEntity.ok(medico.get()) : ResponseEntity.notFound().build();
	}
}
