package lira.leo.mv.uaaserver.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de entidade do usuario
 * @author leonardo.lira
 *
 */
@Entity
@Table(name="user")
public class User {

	@Id
	@Column(length=50,nullable=false)
	private String username;
	
	@Column(length=60,nullable=false)
	private String password;

	public User() {}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
