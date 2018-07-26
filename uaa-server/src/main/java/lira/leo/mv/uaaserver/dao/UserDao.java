package lira.leo.mv.uaaserver.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lira.leo.mv.uaaserver.entity.User;

/**
 * Classe responsável pelo acesso ao banco de dados da entidade User
 * @author leonardo.lira
 *
 */
@Repository
public interface UserDao extends JpaRepository<User,String> {

	/**
	 * Pesquisa o usuario pelo username
	 * 
	 * @param username
	 * @return
	 */
	Optional<User> findByUsername(String username);

}
