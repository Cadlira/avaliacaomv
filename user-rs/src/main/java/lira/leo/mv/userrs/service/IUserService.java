package lira.leo.mv.userrs.service;

import java.util.List;

import lira.leo.mv.userrs.entity.User;

/**
 * Classe de negocio de gerenciamento dos usuarios
 * 
 * @author leonardo.lira
 *
 */
public interface IUserService {

	/**
	 * Salva ou atualiza um usuario
	 * @param user
	 */
	void save(User user);

	/**
	 * Pesquisa um usuario pelo username
	 * @param username
	 * @return
	 */
	User findByUsername(String username);

	/**
	 * Verifica se há usuários cadastrados
	 * @return
	 */
	boolean isEmpty();

	/**
	 * Lista todos os usuarios
	 * @return
	 */
	List<User> listAll();

	/**
	 * 
	 * Verifica se o usuario existe
	 * @param user
	 * @return
	 */
	boolean exists(User user);

	/**
	 * Pesquisa o usuario pelo ID
	 * @param id
	 * @return
	 */
	User findById(long id);

	/**
	 * Deleta o usuario pelo ID 
	 * @param id
	 * @return
	 */
	boolean delete(long id);

}
