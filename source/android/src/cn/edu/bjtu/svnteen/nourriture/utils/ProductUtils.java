package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;

/**
 * Product的工具类
 * @author Tans
 */
public class ProductUtils {

	public static void getAllProducts() {
		StThreadPool.runThread(JobType.NET, new Runnable() {

			@Override
			public void run() {
				final String result;
				HttpGet httpGet = new HttpGet(
						"http://nourriture.sinaapp.com/product/");
				HttpClient httpClient = new DefaultHttpClient();
				try {
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						result = EntityUtils.toString(httpResponse.getEntity());
						final ArrayList<Product> list = JsonUtils.getProducts(result);
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_PRODUCT_JSON,
								new Caller<IProductJsonObserver>() {
									@Override
									public void call() {
										ob.IProductJsonObserver_All(list);
									}

								});
					} else {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_PRODUCT_JSON,
								new Caller<IProductJsonObserver>() {
									@Override
									public void call() {
										ob.IProductJsonObserver_Failed();
									}

								});
					}
				} catch (Exception e) {
					MessageManager.getInstance().asyncNotify(
							MessageID.OBSERVER_PRODUCT_JSON,
							new Caller<IProductJsonObserver>() {
								@Override
								public void call() {
									ob.IProductJsonObserver_Failed();
								}

							});
				}

			}
		});
	}
}
