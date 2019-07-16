package com.giovanni.tw.gerenciadortarefas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//funcao do controller é entender as requisições do usuário,
//feitos através de actions (metodos que atendem às requisições)
@Controller
public class HomeController {

	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("home/home");
		mv.addObject("mensagem", "Mensagem do controller");
		return mv; //indica a view que será devolvida na pasta templates, pasta home e arquivo home.html
	}
}
