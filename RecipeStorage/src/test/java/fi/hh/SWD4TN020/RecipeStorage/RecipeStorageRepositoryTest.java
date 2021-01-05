package fi.hh.SWD4TN020.RecipeStorage;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import fi.hh.SWD4TN020.RecipeStorage.domain.Category;
import fi.hh.SWD4TN020.RecipeStorage.domain.Recipe;
import fi.hh.SWD4TN020.RecipeStorage.domain.RecipeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeStorageRepositoryTest {
	
	@Autowired
	private RecipeRepository repository;
	
	
		@Test
	//Testataan search RecipeRepositoryssa
	public void findByNameShouldReturnRecipe() {
		List<Recipe> recipes = repository.findByNameIgnoreCase("Jamie Oliver's pesto sauce");
		assertThat(recipes).hasSize(1);
		assertThat(recipes.get(0).getIngredients()).isEqualTo("1/2 a clove garlic, 1 big bunch of fresh basil, 1 handful of pine nuts, 1 good handful of freshly grated Parmesan cheese, extra virgin olive oil");		
	}
	
	@Test
	//	//Testataan toimiiko create RecipeRepositoryssa
	public void createNewRecipe() {
		Recipe recipe = new Recipe("Kir Royal", "2 cl mustaherukkalikööri, 12 cl kuohuviini", "Kaada likööri jäähdytettyyn kuohuviinilasiin. Täytä lasi kuohuviinillä.", new Category("Drinks"));
		repository.save(recipe);
		assertThat(recipe.getId()).isNotNull();
	}
	
	@Test
	//Testataan toimiiko delete RecipeRepositoryssa
	public void deleteRecipe() {
		List<Recipe> recipes = repository.findByNameIgnoreCase("Jamie Oliver's pesto sauce");
		assertThat(recipes).hasSize(1);
		repository.deleteById(recipes.get(0).getId());
		//päivitetään lista
		recipes = repository.findByNameIgnoreCase("Jamie Oliver's pesto sauce");
		assertThat(recipes).hasSize(0);
		
	}

}
