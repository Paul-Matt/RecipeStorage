package fi.hh.SWD4TN020.RecipeStorage.web;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.SWD4TN020.RecipeStorage.domain.CategoryRepository;
import fi.hh.SWD4TN020.RecipeStorage.domain.Recipe;
import fi.hh.SWD4TN020.RecipeStorage.domain.RecipeRepository;

@Controller
@EnableAutoConfiguration
public class RecipeController {

	@Autowired
	private RecipeRepository repository;

	@Autowired
	private CategoryRepository crepository;

	// Ohjaus juuresta recipelistiin
	@RequestMapping(value = "/")
	public String start() {
		return "redirect:recipelist";
	}

	// Sisäänkirjautumissivu, huom, ei POST-metodia login-tietojen käsittelyyn
	// controllerissa, koska Spring hoitaa sen
	@RequestMapping(value = "/login")
	public String login() {
		return "Login";
	}

	// Listaa reseptit
	@RequestMapping(value = "/recipelist", method = RequestMethod.GET)
	public String createRecipeList(Model model) {
		model.addAttribute("recipes", repository.findAll());
		return "Recipelist";
	}

	// Listaa reseptit REST
	@RequestMapping(value = "/recipes", method = RequestMethod.GET)
	public @ResponseBody List<Recipe> recipeListRest() {
		return (List<Recipe>) repository.findAll();
	}

	// Haetaan yksi resepti id:n perusteella
	@RequestMapping(value = "/recipes/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Recipe> findRecipeRest(@PathVariable("id") Long recipeId) {
		return repository.findById(recipeId);
	}

	// Mennään Lisää resepti-lomakkeelle
	@RequestMapping(value = "/add")
	public String addSRecipe(Model model) {
		model.addAttribute("recipe", new Recipe());
		model.addAttribute("categories", crepository.findAll());
		return "AddRecipe";
	}

	// tallennetaan uusi resepti, sitten takaisin listaan
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Model model, @Valid Recipe recipe, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
			return "AddRecipe";
		}

		repository.save(recipe);
		return "redirect:recipelist";
	}

	// poistetaan resepti, id välitetään urlissa requestmappingin avulla, voisi olla
	// muukin attribuutti kuin id
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteRecipe(@PathVariable("id") Long recipeId, Model model) {
		repository.deleteById(recipeId);
		return "redirect:../recipelist";
	}

	// mennään reseptin muokkauslomakkeelle
	// välitetään myös kategoria lomakkeelle
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editRecipe(@PathVariable("id") Long recipeId, Model model) {
		model.addAttribute("recipe", repository.findById(recipeId).get());
		model.addAttribute("categories", crepository.findAll());
		return "EditRecipe";
	}

	// tallennetaan muokattu resepti, sitten paluu listaukseen
	@RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
	public String edit(@PathVariable("id") Long recipeId, @Valid Recipe recipe, BindingResult bindingResult, Model model
			) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("categories", crepository.findAll());
			return "EditRecipe";
		}
		repository.save(recipe);
		return "redirect:../recipelist";
	}

}
