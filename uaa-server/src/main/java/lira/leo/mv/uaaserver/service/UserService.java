package lira.leo.mv.uaaserver.service;

import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lira.leo.mv.uaaserver.dao.UserDao;
import lira.leo.mv.uaaserver.entity.User;

/**
 * Implementação do service para manipulação do usuario no serviço de autenticação
 * 
 * @author leonardo.lira
 *
 */
@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao dao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;


	@Override
	public void save(User user) {
		if (Objects.nonNull(user)) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			dao.save(user);
		}

	}

	@Override
	public User findByUsername(String username) {

		if (Objects.nonNull(username)) {
			return dao.findByUsername(username).get();
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return (dao.count() <= 0);
	}

}
