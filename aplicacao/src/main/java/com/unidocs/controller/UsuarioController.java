package com.unidocs.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.unidocs.model.Usuario;
import com.unidocs.repository.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	
	public List<Usuario> listaUsuarios;
	
	@Autowired
	UsuarioRepository repository;
	
	@GetMapping("/cadastrar_usuario")
	public ModelAndView createUsuario(Usuario usuario) {
		ModelAndView modelAndView = new ModelAndView("user_pages/cadastrar_usuario");
		listaUsuarios = (List<Usuario>) repository.findAll();
		modelAndView.addObject("lista", listaUsuarios);
		modelAndView.addObject(usuario);
		return modelAndView;
	}
	
	@PostMapping("/cadastrar_usuario")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return createUsuario(usuario);
		}
		repository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
		return new ModelAndView("redirect:/usuarios/cadastrar_usuario");
	}

	@PostMapping("/editar_usuario/{id}")
	public ModelAndView updateUsuario(@PathVariable Integer id) {
		Usuario usuario = repository.findById(id).orElse(null); 
		return createUsuario(usuario);
	}

	@PostMapping("/excluir_usuario/{id}")
	public String deleteUsuario(@PathVariable Integer id, RedirectAttributes attributes) {
		Usuario usuario = repository.findById(id).orElse(null); 
		repository.delete(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário removido com sucesso!");
		return "redirect:/usuarios/cadastrar_usuario";
	}

}
