package fi.hh.SWD4TN020.RecipeStorage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import fi.hh.SWD4TN020.RecipeStorage.web.UserDetailServiceImpl;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //use user entites instead of in-memory users in authentication
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailServiceImpl userDetailsService;
	
	 @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests().antMatchers("/css/**", "/", "/recipelist").permitAll() // Enable css when logged out
	        .antMatchers("/delete/{id}").hasRole("ADMIN")
	        .and()
	        .authorizeRequests()
	          .anyRequest().authenticated()
	          .and()
	      .formLogin()
	          .loginPage("/login")
	          .defaultSuccessUrl("/recipelist")
	          .permitAll()
	          .and()
	      .logout()
	          .permitAll();
	    }

	    @Autowired
	    //vertailee kryptattuja salasanoja
	    //admin admin käyttis ja salasana
	    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	    }

}
