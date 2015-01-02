package cn.edu.bjtu.svnteen.nourriture.bean;

import java.util.ArrayList;

public class Product {

	private int id;
	private String name;
	private String imageUrl;
	private String description;
	private ArrayList<Ingredient> ingredientArrayList;
	private ArrayList<Nutrition> nutritionArrayList;
	private ArrayList<Tag> tagArrayList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<Ingredient> getIngredientArrayList() {
		return ingredientArrayList;
	}

	public void setIngredientArrayList(ArrayList<Ingredient> ingredientArrayList) {
		this.ingredientArrayList = ingredientArrayList;
	}

	public ArrayList<Nutrition> getNutritionArrayList() {
		return nutritionArrayList;
	}

	public void setNutritionArrayList(ArrayList<Nutrition> nutritionArrayList) {
		this.nutritionArrayList = nutritionArrayList;
	}

	public ArrayList<Tag> getTagArrayList() {
		return tagArrayList;
	}

	public void setTagArrayList(ArrayList<Tag> tagArrayList) {
		this.tagArrayList = tagArrayList;
	}

}
