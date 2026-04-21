package com.projeto_int.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projeto_int.model.FAQ;
import com.projeto_int.repository.FAQRepository;

@Controller
public class FAQController {
	
	@Autowired
	private FAQRepository faqRepository;
	
	@RequestMapping(method = RequestMethod.GET, value = "/cadastrofaq")
	public ModelAndView inicio() {
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofaq");
		modelAndView.addObject("faq_obj", new FAQ());
		
		Iterable<FAQ> areas_conIt = faqRepository.findAll();
		modelAndView.addObject("faq", areas_conIt); 
		
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "**/salvarfaq")
	public ModelAndView salvar(FAQ faq) {
		
		faqRepository.save(faq);
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrofaq");
		Iterable<FAQ> faqIt = faqRepository.findAll();
		
		andView.addObject("faq", faqIt);
		andView.addObject("faq_obj", new FAQ());
		
		return andView;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/listafaq")
	public ModelAndView faq() {
		
		ModelAndView andView = new ModelAndView("cadastro/cadastrofaq");
		Iterable<FAQ> faqIt = faqRepository.findAll();
		
		andView.addObject("faq", faqIt);
		andView.addObject("faq_obj", new FAQ());
		
		return andView;
	}
	
	@GetMapping("/editarfaq/{id_faq}")
	public ModelAndView editar(@PathVariable("id_faq") Long id_faq) {
		
		Optional<FAQ> faq = faqRepository.findById(id_faq);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofaq");
		modelAndView.addObject("faq_obj", faq.get());
		
		return modelAndView;
	}
	
	@GetMapping("/removerfaq/{id_faq}")
	public ModelAndView excluir(@PathVariable("id_faq") Long id_faq) {
		
		faqRepository.deleteById(id_faq);
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofaq");
		modelAndView.addObject("faq", faqRepository.findAll());
		modelAndView.addObject("faq_obj", new FAQ());
		
		return modelAndView;
	}
	
	@PostMapping("**/pesquisarfaq")
	public ModelAndView pesquisar(@RequestParam("nomepesquisa") String nomepesquisa){
		
		ModelAndView modelAndView = new ModelAndView("cadastro/cadastrofaq");
		modelAndView.addObject("faq", faqRepository.findFAQByPergunta(nomepesquisa));
		modelAndView.addObject("faq_obj", new FAQ());
		
		return modelAndView;
	}

}
