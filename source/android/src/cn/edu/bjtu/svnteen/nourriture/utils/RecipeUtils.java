package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.observer.IRecipeJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;

public class RecipeUtils {

	public static void getIngredient(final Recipe recipe) {
		StThreadPool.runThread(JobType.NET, new Runnable() {

			@Override
			public void run() {
				ArrayList<Ingredient> arrayList = IngredientUtils
						.getIngredients();
				ArrayList<Ingredient> localList = recipe.getIngredientList();
				ArrayList<Ingredient> list = new ArrayList<Ingredient>();
				if (arrayList != null) {
					for (Ingredient recipeIngredient : localList) {
						for (Ingredient ingredient : arrayList) {
							if (recipeIngredient.getId() == ingredient.getId()) {
								list.add(ingredient);
							}
						}
					}
					recipe.setIngredientList(list);
					MessageManager.getInstance().asyncNotify(
							MessageID.OBSERVER_RECIPE_JSON,
							new Caller<IRecipeJsonObserver>() {
								@Override
								public void call() {
									ob.IRecipeJsonObserver_Detail_ingredients(recipe);
								}

							});
				}
			}
		});
	}

	public static void getRecipes() {
		HttpUtils.get(UrlManagerUtils.getRecipeUrl(0),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						final ArrayList<Recipe> list = JsonUtils
								.getRecipes(result);
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_RECIPE_JSON,
								new Caller<IRecipeJsonObserver>() {
									@Override
									public void call() {
										ob.IRecipeJsonObserver_All(list);
									}

								});

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {

					}
				});
	}
}
