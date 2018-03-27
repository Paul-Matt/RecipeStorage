package fi.hh.SWD4TN020.RecipeStorage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import fi.hh.SWD4TN020.RecipeStorage.domain.Category;
import fi.hh.SWD4TN020.RecipeStorage.domain.CategoryRepository;
import fi.hh.SWD4TN020.RecipeStorage.domain.Recipe;
import fi.hh.SWD4TN020.RecipeStorage.domain.RecipeRepository;
import fi.hh.SWD4TN020.RecipeStorage.domain.User;
import fi.hh.SWD4TN020.RecipeStorage.domain.UserRepository;

@SpringBootApplication
public class RecipeStorageApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RecipeStorageApplication.class);
	}

	private static final Logger log = LoggerFactory.getLogger(RecipeStorageApplication.class);

	public static void main(String[] args) throws Exception {
		SpringApplication.run(RecipeStorageApplication.class, args);
	}

	@Bean
	public CommandLineRunner recipeDemo(RecipeRepository repository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save a few recipes");
			crepository.save(new Category("Sauces"));
			crepository.save(new Category("Soups"));
			crepository.save(new Category("Main course"));
			crepository.save(new Category("Desserts"));
			repository
					.save(new Recipe("Jamie Oliver's pesto sauce",
							"1/2 a clove garlic, 1 big bunch of fresh basil, 1 handful of pine nuts, 1 good handful of freshly grated Parmesan cheese, extra virgin olive oil" ,
							"Peel the garlic, then pound in a pestle and mortar with a pinch of sea salt. Pick, roughly chop and add the basil leaves, then bash to a paste (or pulse in a food processor). Add the pine nuts (very lightly toast first, if you like) to the mixture and pound again, then stir in half the Parmesan. Drizzle in some oil – you need just enough to bind the sauce and get it to an oozy consistency. Add most of the remaining cheese, then season to perfection with salt and black pepper. Have a taste, and keep adding a bit more cheese or oil until you are happy with the taste and consistency.",
							crepository.findByName("Sauces").get(0)));
			repository.save(new Recipe("Sweet potato soup",
					"600-800g sweet potato, 2 onions, 3-5 garlic cloves, 4dl water,1/2 stock cube, 4dl coconut milk, 1 tsp sambal oelek chili paste, juice of two limes or one lemon",
					"Peel and chop the sweet potato into cubes. Peel and chop onions and garlic. Heat the water, and coconut milk in a pot. Add stock cube, sweet potato, onions, garlic and sambal oelek paste. Heat until boiling, and let the soup boil for about 15 min or until sweet potato is cooked enough. Use a mixer to puree the soup. Add the lime or lemon juice.",
					crepository.findByName("Soups").get(0)));
			
			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$PKLObypWMb8NDC4fEDX3ee3A7wPTTfijqBD1deoRSrwvauzYbFv6S",
					"useremail@mail.fi", "ROLE_USER");
			User user2 = new User("admin", "$2a$10$3He7LAyyIULAy0gMB0YcNucnl4QjbxOwgbzXLtj9DdWvuJ/fuGMt2",
					"adminemail@mail.fi", "ROLE_ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			log.info("fetch all recipes");
			for (Recipe recipe : repository.findAll()) {
				log.info(recipe.toString());

			}
		};
	}
}
