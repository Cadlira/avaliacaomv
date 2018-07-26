package lira.leo.mv.uaaserver.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lira.leo.mv.uaaserver.dao.UserDao;
import lira.leo.mv.uaaserver.security.model.MVUserDetails;

/**
 * Implementação do UserDatilsService carregando o usuário do banco
 * 
 * @author leonardo.lira
 *
 */
@Service
public class MVUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return dao.findByUsername(username).map(usuario -> new MVUserDetails(usuario))
				.orElseThrow(() -> new UsernameNotFoundException("Usuário " + username + " não encontrado"));
	}
}
