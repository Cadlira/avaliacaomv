package lira.leo.mv.userrs.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lira.leo.mv.userrs.entity.User;
import lira.leo.mv.userrs.service.IUserService;
import lira.leo.mv.userrs.web.exception.ValidationException;
import lira.leo.mv.userrs.web.validator.UserValidator;

/**
 * Classe responsável pela API de gerenciamento do usuário
 * @author leonardo.lira
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService service;

	@Autowired
	private UserValidator validator;

	
	/**
	 * Adiciona o Validator do usuario as chamadas
	 * 
	 * @param binder
	 * @param request
	 */
	@InitBinder("user")
	protected void initiBinder(WebDataBinder binder, HttpServletRequest request) {
		validator.setMetodo(RequestMethod.valueOf(request.getMethod()));
		binder.addValidators(validator);
	}
	
	/**
	 * 
	 * Salva um novo usuario no serviço de usuario
	 * @param user
	 * @param result
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.POST)
	@ApiOperation(value = "Cadastra um usuário no sistema",response = User.class)
	public ResponseEntity<User> save(@RequestBody @Valid User user, BindingResult result,
			HttpServletResponse response) {

		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		service.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
		
	}

	/**
	 * Atualiza um usuario no serviço
	 * @param user
	 * @param result
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.PUT)
	@ApiOperation(value = "Atualiza um usuário no sistema",response = User.class)
	public ResponseEntity<User> update(@RequestBody @Valid User user, BindingResult result,
			HttpServletResponse response) {

		if (result.hasErrors()) {
			throw new ValidationException(result);
		}
		
		service.save(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * Pesquisa um usuario pelo username
	 * @param username
	 * @return
	 */
	@RequestMapping(value = { "/findByUsername/{username}" }, method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisa o usuario pelo username",response = User.class)
	public ResponseEntity<User> findByUsername(@PathVariable("username") String username) {

		return new ResponseEntity<User>(service.findByUsername(username), HttpStatus.OK);
	}

	/**
	 * Pesquisa um usuario pelo ID
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.GET)
	@ApiOperation(value = "Pesquisa o usuario pelo id",response = User.class)
	public ResponseEntity<User> findById(@PathVariable("id") long id) {

		return new ResponseEntity<User>(service.findById(id), HttpStatus.OK);
	}

	/**
	 * Deleta o usuario com o ID especificado
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/{id}" }, method = RequestMethod.DELETE)
	@ApiOperation(value = "Deleta o usuário do sistema",response = User.class)
	public ResponseEntity<Boolean> delete(@PathVariable("id") long id) {
		return new ResponseEntity<Boolean>(service.delete(id), HttpStatus.OK);
	}

	/**
	 * Lista todos os usuário
	 * @return
	 */
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	@ApiOperation(value = "Lista todos os usuários cadastrados",response = User.class)
	public ResponseEntity<List<User>> users() {
		
		return new ResponseEntity<List<User>>(service.listAll(), HttpStatus.OK);
		
	}
}
