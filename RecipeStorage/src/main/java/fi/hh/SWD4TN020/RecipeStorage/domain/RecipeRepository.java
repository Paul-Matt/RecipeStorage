package fi.hh.SWD4TN020.RecipeStorage.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository <Recipe, Long> {
	List<Recipe>findByNameIgnoreCase(String name);

}
