package br.com.fake.controller;


import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.fake.model.Paciente;
import br.com.fake.service.PacienteService;

@Controller
@RequestMapping("/pacientes")
public class PacienteController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PacienteService service;

	@GetMapping("/novo")
	public ModelAndView novo(Paciente paciente) {
		ModelAndView mv = new ModelAndView("pacientes/add");
		mv.addObject("paciente", paciente);
		return mv;
	}

	@GetMapping("/list")
	public ModelAndView lista(){
		ModelAndView mv = new ModelAndView("pacientes/list");
		mv.addObject("pacientes", this.service.lista());
		return mv;
	}

	@PostMapping("/save")		
	public ModelAndView salva(@Valid Paciente paciente, BindingResult result, Model model, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/pacientes/list");

		if (result.hasErrors()) {
			return novo(paciente);
		}

		attributes.addFlashAttribute("mensagem", "Paciente salvo com sucesso");
		this.service.salva(paciente);
		return mv;}

}
