package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.IngredientDataModel;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.observer.IIngredientJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;

public class IngredientUtils {

	// 非异步方法
	public static ArrayList<Ingredient> getIngredients() {
		ArrayList<Ingredient> ingredientArrayList = null;
		HttpGet httpGet = new HttpGet(UrlManagerUtils.getIngredientUrl(0));
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				ingredientArrayList = JsonUtils.getIngredients(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ingredientArrayList;
	}

	public static void getIngredientsData() {
		StThreadPool.runThread(JobType.NET, new Runnable() {

			@Override
			public void run() {
				final ArrayList<Ingredient> arrayList = IngredientUtils
						.getIngredients();

				MessageManager.getInstance().asyncNotify(
						MessageID.OBSERVER_INGREDIENT_JSON,
						new Caller<IIngredientJsonObserver>() {
							@Override
							public void call() {
								ob.IIngredientJsonObserver_ALL(arrayList);
							}

						});
			}
		});

	}

	// 把无序的从网上请求下来的ingredient根据category分类
	public static ArrayList<IngredientDataModel> getConvertedIngredients(
			ArrayList<Ingredient> list) {
		boolean hasCategory = false;
		ArrayList<IngredientDataModel> dstList = new ArrayList<IngredientDataModel>();
		if (list != null && list.size() > 0) {
			for (Ingredient ingredient : list) {
				for (int i = 0; i < dstList.size(); i++) {
					if (dstList.get(i).getId() == ingredient.getCategory()
							.getId()) {
						dstList.get(i).getIngredientList().add(ingredient);
						hasCategory = true;
						break;
					}
				}
				if (!hasCategory) {
					IngredientDataModel dataModel = new IngredientDataModel();
					dataModel.setId(ingredient.getCategory().getId());
					dataModel.setName(ingredient.getCategory().getName());
					dataModel.setIngredientList(new ArrayList<Ingredient>());
					dataModel.getIngredientList().add(ingredient);
					dstList.add(dataModel);
				}
				hasCategory = false;
			}
		}
		return dstList;
	}
}
