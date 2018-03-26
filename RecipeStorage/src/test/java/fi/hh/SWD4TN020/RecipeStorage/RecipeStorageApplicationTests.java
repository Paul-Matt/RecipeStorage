package fi.hh.SWD4TN020.RecipeStorage;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.SWD4TN020.RecipeStorage.web.RecipeController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeStorageApplicationTests {
	
	@Autowired
	private RecipeController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

}
