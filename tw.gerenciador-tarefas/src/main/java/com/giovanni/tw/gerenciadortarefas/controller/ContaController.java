package com.giovanni.tw.gerenciadortarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.giovanni.tw.gerenciadortarefas.models.Usuario;
import com.giovanni.tw.gerenciadortarefas.servicos.ServicoUsuario;

@Controller
public class ContaController {

	@Autowired
	private ServicoUsuario servicoUsuario;
	
	@GetMapping("/login")
	public String login() {
		
		return "conta/login.html";
	}
	
	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("conta/registrar");
		Usuario user = new Usuario();
		mv.addObject("usuario", user);
		return mv;
	}
	
	@PostMapping("/registration")
	public ModelAndView registration (@Valid Usuario usuario, BindingResult result) {
		ModelAndView mv = new ModelAndView();
		Usuario usr = servicoUsuario.procurarPorEmail(usuario.getEmail());
		
		if(usr!=null) {
			System.out.println("EMAIL NULO 2");
			result.rejectValue("email","", "Usuário já cadastrado");
		}
		if(result.hasErrors()) {
			System.out.println("TEM ERROS(?)");
			mv.setViewName("conta/registrar");
			mv.addObject("usuario", usuario);
			mv.setViewName("redirect:/login");
		}	
		return mv;
		
	}
}
