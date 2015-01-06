package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class LoginUtils {
	private static final String GRANT_TYPE_KEY = "grant_type";
	private static final String GRANT_TYPE_VALUE = "password";
	private static final String USERNAME = "username";
	private static final String PASSWORD = "password";
	private static final String CLIENT_ID_KEY = "client_id";
	private static final String CLIENT_ID_VALUE = "fLPDC=Nvj8tl0vChOZRx9hvcryBZje@FWfU5PGFP";
	private static final String CLIENT_SECRET_KEY = "client_secret";
	private static final String CLIENT_SECRET_VALUE = "IAHaGKoGc9i:DzJ@-C8yAnhDo-UHkUL?Liw0A5ma15jdSFZYCkuCzghdHW3HRdkA9RqR1P:gQGBZtl4H3qClRxCFLpZKHd3e?We_mMaoOCWQkMB2e@vpXf-NxRR@dyWg";

	public static int login(String name, String password) {
		int returnVal = 0;
		String url = UrlManagerUtils.getLoginPostUrl();
		HttpClient httpClient = new DefaultHttpClient();
		HttpResponse httpResponse = null;
		HttpPost httpPost = new HttpPost(url);
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair(GRANT_TYPE_KEY, GRANT_TYPE_VALUE));
		params.add(new BasicNameValuePair(USERNAME, name));
		params.add(new BasicNameValuePair(PASSWORD, password));
		params.add(new BasicNameValuePair(CLIENT_ID_KEY, CLIENT_ID_VALUE));
		params.add(new BasicNameValuePair(CLIENT_SECRET_KEY,
				CLIENT_SECRET_VALUE));
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			httpResponse = httpClient.execute(httpPost);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(httpResponse.getEntity());
				JSONObject jsonObject = new JSONObject(result);
				String token = jsonObject.getString("access_token");
				PreferenceUtils.saveToken(token);
				PreferenceUtils.saveLogin(true);
				PreferenceUtils.saveUserName(name);
				returnVal = 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnVal;
	}

}
