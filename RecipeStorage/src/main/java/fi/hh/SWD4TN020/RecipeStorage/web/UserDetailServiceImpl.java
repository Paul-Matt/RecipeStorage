package fi.hh.SWD4TN020.RecipeStorage.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fi.hh.SWD4TN020.RecipeStorage.domain.User;
import fi.hh.SWD4TN020.RecipeStorage.domain.UserRepository;


/**
 * This class is used by spring security to authenticate and authorize user
 **/
// auttaa autentikoimaan käyttäjän
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	private final UserRepository repository;

	@Autowired
	public UserDetailServiceImpl(UserRepository userRepository) {
		this.repository = userRepository;
	}

	@Override
	/*
	 * tärkeintä että hakee usernamella salasanan, lataa käyttäjän tiedot käyttäjän
	 * nimellä loadUserByUsername, service-luokka vertailee käyttiksen ja salasanan, Spring käyttää servicen kautta repositorya
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> currusers = repository.findByUsername(username);
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currusers.get(0).getPasswordHash(),
				AuthorityUtils.createAuthorityList(currusers.get(0).getRole()));
		return user;
	}
}
