package fi.hh.SWD4TN020.RecipeStorage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.SWD4TN020.RecipeStorage.domain.User;
import fi.hh.SWD4TN020.RecipeStorage.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository urepository;
	
	@Test
	//Testataan search UserRepositoryssa
	public void findByUsernameShouldReturnUser() {
		List<User> users = urepository.findByUsername("user");
		assertThat(users.get(0).getEmail()).isEqualTo("useremail@mail.fi");
	}
	
	@Test
	// testataan toimiiko create UserRepositoryssa
	public void createNewUser() {
		User user2 = new User("admin2", "$2a$10$3He7LAyyIULAy0gMB0YcNucnl4QjbxOwgbzXLtj9DdWvuJ/fuGMt2",
				"adminemail2@mail.fi", "ADMIN");
		urepository.save(user2);
		assertThat(user2.getId()).isNotNull();
	}
	
	@Test
	//Testataan toimiiko delete CategoryRepositoryssa
	public void deleteUser() {
		List<User> users = urepository.findByUsername("admin");
		assertThat( users).hasSize(1);
		urepository.deleteById(users.get(0).getId());
		//päivitetään lista
		users = urepository.findByUsername("admin");
		assertThat(users).hasSize(0);
		
	}


}
