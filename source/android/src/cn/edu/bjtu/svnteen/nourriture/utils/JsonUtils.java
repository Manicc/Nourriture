package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.TextUtils;
import cn.edu.bjtu.svnteen.nourriture.bean.Comment;
import cn.edu.bjtu.svnteen.nourriture.bean.Favorite;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.IngredientCategory;
import cn.edu.bjtu.svnteen.nourriture.bean.Nutrition;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public class JsonUtils {
	// [{"content":{"id":3,"name":"\u767d\u83dc"},"type":0},
	// {"content":{"id":4,"name":"\u867e\u4ec1"},"type":0},{"content":{"id":5,"name":"\u8c46\u8150"},"type":0},{"content":{"id":6,"name":"\u4e94\u82b1\u8089"},"type":0}]
	public static Favorite getFavorite(String json) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		Favorite favorite = new Favorite();
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject;
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				int type = jsonObject.getInt("type");
				String contentStr = jsonObject.getString("content");
				JSONObject contentJsonObj = new JSONObject(contentStr);
				int id = contentJsonObj.getInt("id");
				String name = contentJsonObj.getString("name");
				switch (type) {
				case 0:// ingredient
					Ingredient ingredient = new Ingredient();
					ingredient.setId(id);
					ingredient.setName(name);
					favorite.getIngredientList().add(ingredient);
					break;
				case 1:// product
					Product product = new Product();
					product.setId(id);
					product.setName(name);
					favorite.getProductList().add(product);
					break;
				case 2:// recipe
					Recipe recipe = new Recipe();
					recipe.setID(id);
					recipe.setName(name);
					favorite.getRecipeList().add(recipe);
					break;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return favorite;
	}

	public static ArrayList<Recipe> getRecipes(String json) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		Ingredient ingredient;
		ArrayList<Ingredient> ingredientArrayList;
		Nutrition nutrition;
		ArrayList<Nutrition> nutritionArrayList;
		ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject;
			Recipe recipe;
			for (int i = 0; i < jsonArray.length(); i++) {
				ingredientArrayList = new ArrayList<Ingredient>();
				nutritionArrayList = new ArrayList<Nutrition>();
				recipe = new Recipe();
				jsonObject = jsonArray.getJSONObject(i);
				recipe.setID(jsonObject.getInt("id"));
				recipe.setName(jsonObject.getString("name"));
				recipe.setProcessing(jsonObject.getString("processing"));
				recipe.setImageUrl(jsonObject.getString("image"));
				recipe.setFoodType(jsonObject.getString("food_type"));
				JSONArray ingredientJsonArray = new JSONArray(
						jsonObject.getString("ingredients"));
				for (int j = 0; j < ingredientJsonArray.length(); j++) {
					ingredient = new Ingredient();
					ingredient.setId(ingredientJsonArray.getInt(j));
					ingredientArrayList.add(ingredient);
				}
				JSONArray nutritionJsonArray = new JSONArray(
						jsonObject.getString("nutrition"));
				recipe.setIngredientList(ingredientArrayList);

				for (int k = 0; k < nutritionJsonArray.length(); k++) {
					nutrition = new Nutrition();
					nutrition.setId(nutritionJsonArray.getInt(k));
					nutritionArrayList.add(nutrition);
				}
				recipe.setNutritionList(nutritionArrayList);
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
			IngredientCategory category;
			for (int i = 0; i < jsonArray.length(); i++) {
				category = new IngredientCategory();
				ingredient = new Ingredient();
				jsonObject = jsonArray.getJSONObject(i);
				ingredient.setId(jsonObject.getInt("id"));
				ingredient.setName(jsonObject.getString("name"));
				ingredient.setImageURL(jsonObject.getString("image"));
				JSONObject categoryJson = jsonObject.getJSONObject("category");
				category.setId(categoryJson.getInt("id"));
				category.setName(categoryJson.getString("name"));
				ingredient.setCategory(category);
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

	public static ArrayList<Comment> getCommentArray(String json) {
		if (TextUtils.isEmpty(json)) {
			return null;
		}
		ArrayList<Comment> commentList = new ArrayList<Comment>();
		try {
			JSONArray jsonArray = new JSONArray(json);
			Comment comment;
			for (int i = 0; i < jsonArray.length(); i++) {
				comment = new Comment();
				JSONObject jsonObject = jsonArray.getJSONObject(i);
				String content = jsonObject.getString("content");
				String time = jsonObject.getString("time");
				String jsonUser = jsonObject.getString("user");
				JSONObject userObject = new JSONObject(jsonUser);
				int id = userObject.getInt("id");
				String username = userObject.getString("username");
				comment.setId(id);
				comment.setName(username);
				comment.setContent(content);
				comment.setTime(time);
				commentList.add(comment);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return commentList;
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
