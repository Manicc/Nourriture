package cn.edu.bjtu.svnteen.nourriture.bean;

import java.util.ArrayList;

public class Favorite {

	private ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
	private ArrayList<Product> productList = new ArrayList<Product>();
	private ArrayList<Recipe> recipeList = new ArrayList<Recipe>();

	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}

	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

	public ArrayList<Product> getProductList() {
		return productList;
	}

	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}

	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}

	public void setRecipeList(ArrayList<Recipe> recipeList) {
		this.recipeList = recipeList;
	}

}
