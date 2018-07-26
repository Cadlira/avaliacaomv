package lira.leo.mv.uaaserver.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lira.leo.mv.uaaserver.entity.User;
import lira.leo.mv.uaaserver.service.IUserService;

/**
 * Classe de acesso as telas e apis do servico de autenticação
 * 
 * @author Leonardo Lira 
 */
@Controller
@RequestMapping("/")
public class UserController {

	@Autowired
	private IUserService service;
	
	/**
	 * retorna as informações do usuario logado.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/me")
	@ResponseBody
	public Principal getCurrentLoggedInUser(Principal user) {
		return user;
	}

	/**
	 * Registra um novo usuario no serviço de autenticação e redireciona para a tela de login
	 * 
	 * @param user
	 * @param request
	 * @param result
	 * @return 
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, HttpServletRequest request, BindingResult result) {

		this.service.save(user);
		
		return "redirect:/login?registred";
	}
	
	/**
	 * Restra um novo usuario através de uma API rest
	 * 
	 * @param user
	 * @param request
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/rest/register", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Boolean> restRegister(@RequestBody @Valid User user , HttpServletRequest request, BindingResult result) {
		
		this.service.save(user);
		
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
