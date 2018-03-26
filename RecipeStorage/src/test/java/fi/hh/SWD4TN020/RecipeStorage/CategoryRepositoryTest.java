package fi.hh.SWD4TN020.RecipeStorage;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.SWD4TN020.RecipeStorage.domain.Category;
import fi.hh.SWD4TN020.RecipeStorage.domain.CategoryRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository crepository;
	
	@Test
	//Testataan search CategoryRepositoryssa
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crepository.findByName("Desserts");
		
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getId()).isEqualTo(4L);
	}
	
	@Test
	//	//Testataan toimiiko create CategoryRepositoryssa
	public void createNewCategory() {
		Category category = new Category("Drinks");
		crepository.save(category);
		assertThat(category.getId()).isNotNull();
	}
	
	@Test
	//Testataan toimiiko delete CategoryRepositoryssa
	public void deleteCategory() {
		List<Category> categories= crepository.findByName("Soups");
		assertThat(categories).hasSize(1);
		crepository.deleteById(categories.get(0).getId());
		//päivitetään lista
		categories= crepository.findByName("Soups");
		assertThat(categories).hasSize(0);
		
	}

}
