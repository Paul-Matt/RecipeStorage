package fi.hh.SWD4TN020.RecipeStorage.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	@Size(min=1, max=300)
	private String name;
	@NotNull
	@Size(min=1, max=3000)
	@Column(columnDefinition = "TEXT")
	private String ingredients;
	@NotNull
	@Size(min=1, max=3000)
	@Column(columnDefinition = "TEXT")
	private String instructions;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "categoryid")
	private Category category;

	public Recipe() {
	}

	public Recipe(Long id, String name, String ingredients, String instructions, Category category) {
		super();
		this.id = id;
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.category = category;
	}

	public Recipe(String name, String ingredients, String instructions, Category category) {
		super();
		this.name = name;
		this.ingredients = ingredients;
		this.instructions = instructions;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		if (this.category != null)
			return "Recipe [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", instructions="
					+ instructions + ", category=" + this.getCategory() + "]";
		else
			return "Recipe [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", instructions="
					+ instructions + "]";
	}

}
