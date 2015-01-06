package cn.edu.bjtu.svnteen.nourriture.utils;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import cn.edu.bjtu.svnteen.nourriture.bean.Favorite;

public class FavoriteUtils {

	public static Favorite getFavorites() {
		Favorite favorite = new Favorite();
		HttpGet httpGet = new HttpGet(UrlManagerUtils.getFavoriteUrl());
		// httpGet.addHeader("HTTP_AUTHORIZATION", "Authorization");
		httpGet.addHeader("AUTHORIZATION",
				"Bearer " + PreferenceUtils.getToken());

		// PreferenceUtils.getToken());
		HttpClient httpClient = new DefaultHttpClient();
		try {
			HttpResponse httpResponse = httpClient.execute(httpGet);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				favorite = JsonUtils.getFavorite(result);
			}
		} catch (Exception e) {
			String b = e.toString();
		}
		return favorite;
	}
}
