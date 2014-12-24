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
}
