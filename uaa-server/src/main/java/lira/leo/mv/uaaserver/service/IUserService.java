package lira.leo.mv.uaaserver.service;

import lira.leo.mv.uaaserver.entity.User;

/**
 * Interface do service de usuario
 * 
 * @author leonardo.lira
 */
public interface IUserService {

	/**
	 * Insere/atualiza um usuario no serviço de autenticação
	 * @param user
	 */
	public void save(User user);
	
	/**
	 * pequisa um usuario pelo username
	 * 
	 * @param username
	 * @return
	 */
	public User findByUsername(String username);
	
	/**
	 * verifica se o banco está vazio
	 * 
	 * @return
	 */
	public boolean isEmpty();
	
}
