package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.text.TextUtils;

import cn.edu.bjtu.svnteen.nourriture.bean.Product;

public class JsonUtils {

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
}
