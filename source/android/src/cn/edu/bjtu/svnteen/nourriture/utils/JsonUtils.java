package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.Nutrition;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public class JsonUtils {

	public static ArrayList<Recipe> getRecipes(String json) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject;
			Recipe recipe;
			for (int i = 0; i < jsonArray.length(); i++) {
				recipe = new Recipe();
				jsonObject = jsonArray.getJSONObject(i);
				recipe.setID(jsonObject.getInt("id"));
				recipe.setName(jsonObject.getString("name"));
				recipe.setProcessing(jsonObject.getString("processing"));
				recipe.setImageUrl(jsonObject.getString("image"));
				recipe.setFoodType(jsonObject.getString("food_type"));
				recipeList.add(recipe);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return recipeList;
	}

	public static ArrayList<Ingredient> getIngredients(String json) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject;
			Ingredient ingredient;
			for (int i = 0; i < jsonArray.length(); i++) {
				ingredient = new Ingredient();
				jsonObject = jsonArray.getJSONObject(i);
				ingredient.setId(jsonObject.getInt("id"));
				ingredient.setName(jsonObject.getString("name"));
				ingredient.setImageURL(jsonObject.getString("image"));
				ingredientList.add(ingredient);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ingredientList;
	}

	public static ArrayList<Product> getProducts(String json) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject;
			Product product;
			for (int i = 0; i < jsonArray.length(); i++) {
				product = new Product();
				jsonObject = jsonArray.getJSONObject(i);
				product.setId(jsonObject.getInt("id"));
				product.setName(jsonObject.getString("name"));
				product.setImageUrl(jsonObject.getString("image"));
				productList.add(product);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return productList;
	}

	public static void getProductDetail(Product product, String json) {
		if (TextUtils.isEmpty(json)) {
			return;
		}
		JSONObject jsonObject;
		Ingredient ingredient;
		ArrayList<Ingredient> ingredientArrayList = new ArrayList<Ingredient>();
		Nutrition nutrition;
		ArrayList<Nutrition> nutritionArrayList = new ArrayList<Nutrition>();
		try {
			jsonObject = new JSONObject(json);
			String description = jsonObject.getString("desc");
			product.setDescription(description);
			JSONArray ingredientJsonArray = new JSONArray(
					jsonObject.getString("ingredients"));
			for (int j = 0; j < ingredientJsonArray.length(); j++) {
				ingredient = new Ingredient();
				ingredient.setId(ingredientJsonArray.getInt(j));
				ingredientArrayList.add(ingredient);
			}
			JSONArray nutritionJsonArray = new JSONArray(
					jsonObject.getString("nutrition"));
			product.setIngredientArrayList(ingredientArrayList);

			for (int k = 0; k < nutritionJsonArray.length(); k++) {
				nutrition = new Nutrition();
				nutrition.setId(nutritionJsonArray.getInt(k));
				nutritionArrayList.add(nutrition);
			}
			product.setNutritionArrayList(nutritionArrayList);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
