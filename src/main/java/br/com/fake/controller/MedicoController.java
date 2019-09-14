/**
 * 
 */
package br.com.fake.controller;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fake.model.Medico;
import br.com.fake.service.MedicoService;

/**
 * @author carlosbarbosagomesfilho
 * Aug 30, 2019
 * 
 */
@Controller
@RequestMapping("/medicos")
public class MedicoController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private MedicoService service;

	@GetMapping("/novo")
	public ModelAndView novo(Medico medico) {
		ModelAndView mv = new ModelAndView("medicos/add");
		mv.addObject("medico", medico);
		return mv;
	}

	@GetMapping("/list")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("medicos/list");
		mv.addObject("medicos", this.service.lista());
		return mv;
	}

	@PostMapping("/save")		
	public ModelAndView salva(@Valid Medico medico, BindingResult result, Model model, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos/list");

		if (result.hasErrors()) {
			return novo(medico);
		}

		attributes.addFlashAttribute("mensagem", "Contato salvo com sucesso");
		this.service.salva(medico);
		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete (@PathVariable long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/medicos/list");
		this.service.delete(id);
		attributes.addFlashAttribute("mensagem", "Médico removido com seucesso");

		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit (@PathVariable long id, @Valid Medico medico, BindingResult result,  RedirectAttributes attributes){
		ModelAndView mv = new ModelAndView("redirect:/medicos/novo");
		
		Optional<Medico> m = this.service.getMedicoByID(id);
		
		if(m.isPresent()) {
			medico.setId(m.get().getId());
		}

		mv.addObject(medico);
		this.service.salva(medico);
				
		attributes.addFlashAttribute("mensagem", "Médico editado com seucesso");

		return mv;

	}

}
