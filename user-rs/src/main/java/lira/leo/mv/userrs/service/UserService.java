package lira.leo.mv.userrs.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lira.leo.mv.userrs.dao.UserDao;
import lira.leo.mv.userrs.entity.User;

@Service
@Transactional
public class UserService implements IUserService {
	
	@Autowired
	private UserDao dao;
	
	@Override
	public void save(User user) {
		if (Objects.nonNull(user)) {
			dao.save(user);
		}

	}
	
	@Override
	public User findById(long id){
		Optional<User> user = dao.findById(id);
		if (user.isPresent()) {
			return user.get();
		} else {
			return null;
		}
	}
	
	@Override
	public User findByUsername(String username) {

		if (Objects.nonNull(username)) {
			Optional<User> user = dao.findByUsername(username);
			if (user.isPresent()) {
				return user.get();
			} else {
				return null;
			}
		}

		return null;
	}

	@Override
	public boolean isEmpty() {
		return (dao.count() <= 0);
	}
	
	@Override
	public List<User> listAll(){
		return dao.findAll();
	}
	
	@Override
	public boolean exists(User user){
		List<User> users = dao.findByUsernameOrId(user.getUsername(), user.getId());
		
		return (Objects.nonNull(users) && !users.isEmpty());
	}
	
	@Override
	public boolean delete(long id){
		dao.deleteById(id);
		return true;
	}
}
