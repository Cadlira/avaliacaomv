package lira.leo.mv.userrs.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lira.leo.mv.userrs.entity.User;

@Repository
public interface UserDao extends JpaRepository<User,Long> {
	Optional<User> findByUsername(String username);
	
	List<User> findByUsernameOrId(String username, long id);
	
}
