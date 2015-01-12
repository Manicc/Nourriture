package cn.edu.bjtu.svnteen.nourriture.utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * HttpUtils 网络加载数据和缓存数据工具类
 * 
 * @author Tans
 */
public class HttpUtils {

	private static AsyncHttpClient mClient = new AsyncHttpClient();

	/**
	 * http get请求不带参数
	 * 
	 * @param url
	 * @param res
	 */
	public static void get(String url, AsyncHttpResponseHandler res) {
		mClient.get(url, res);
	}

	/**
	 * http get 请求带参数
	 * 
	 * @param url
	 * @param params
	 * @param res
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler res) {
		mClient.get(url, params, res);
	}

}
