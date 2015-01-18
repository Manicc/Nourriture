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
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;

/**
 * Product的工具类
 * 
 * @author Tans
 */
public class ProductUtils {

	public static void getIngredient(final Product product) {
		StThreadPool.runThread(JobType.NET, new Runnable() {

			@Override
			public void run() {
				ArrayList<Ingredient> arrayList = IngredientUtils
						.getIngredients();
				ArrayList<Ingredient> list = new ArrayList<Ingredient>();
				if (arrayList != null) {
					for (Ingredient productIngredient : product
							.getIngredientArrayList()) {
						for (Ingredient ingredient : arrayList) {
							if (productIngredient.getId() == ingredient.getId()) {
								list.add(ingredient);
							}
						}
					}
					product.setIngredientArrayList(list);
					MessageManager.getInstance().asyncNotify(
							MessageID.OBSERVER_PRODUCT_JSON,
							new Caller<IProductJsonObserver>() {
								@Override
								public void call() {
									ob.IProductJsonObserver_Detail_ingredients(product);
								}

							});
				}
			}
		});
	}

	public static void getProductDetail(final Product product) {

		HttpUtils.get(UrlManagerUtils.getProductUrl(product.getId()),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						JsonUtils.getProductDetail(product, result);
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_PRODUCT_JSON,
								new Caller<IProductJsonObserver>() {
									@Override
									public void call() {
										ob.IProductJsonObserver_Detail(product);
									}

								});
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_PRODUCT_JSON,
								new Caller<IProductJsonObserver>() {
									@Override
									public void call() {
										ob.IProductJsonObserver_Failed();
									}

								});
					}
				});
	}

	public static void getAllProducts() {
		HttpUtils.get(UrlManagerUtils.getProductUrl(0),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						String result = new String(arg2);
						final ArrayList<Product> list = JsonUtils
								.getProducts(result);
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_PRODUCT_JSON,
								new Caller<IProductJsonObserver>() {
									@Override
									public void call() {
										ob.IProductJsonObserver_All(list);
									}

								});
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_PRODUCT_JSON,
								new Caller<IProductJsonObserver>() {
									@Override
									public void call() {
										ob.IProductJsonObserver_Failed();
									}

								});
					}
				});
	}
}
