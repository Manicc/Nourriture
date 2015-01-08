package cn.edu.bjtu.svnteen.nourriture.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import cn.edu.bjtu.svnteen.nourriture.bean.Comment;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;
import cn.edu.bjtu.svnteen.nourriture.observer.ICommentObserver;

public class CommentUtils {

	public static void getComment(final Ingredient ingredient) {
		StThreadPool.runThread(JobType.NET, new Runnable() {

			@Override
			public void run() {
				HttpGet httpGet = new HttpGet(UrlManagerUtils
						.getCommentUrl(ingredient));
				HttpClient httpClient = new DefaultHttpClient();
				try {
					HttpResponse httpResponse = httpClient.execute(httpGet);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						String result = EntityUtils.toString(httpResponse
								.getEntity());
						final ArrayList<Comment> commentList = JsonUtils
								.getCommentArray(result);
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_COMMENT,
								new Caller<ICommentObserver>() {
									@Override
									public void call() {
										ob.ICommentObserver_success(commentList);
									}

								});
					} else {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_COMMENT,
								new Caller<ICommentObserver>() {
									@Override
									public void call() {
										ob.ICommentObserver_failed();
									}

								});
					}
				} catch (Exception e) {
					MessageManager.getInstance().asyncNotify(
							MessageID.OBSERVER_COMMENT,
							new Caller<ICommentObserver>() {
								@Override
								public void call() {
									ob.ICommentObserver_failed();
								}

							});
				}

			}
		});
	}

	public static void sendCommend(final Ingredient ingredient,
			final String content) {
		StThreadPool.runThread(JobType.NET, new Runnable() {

			@Override
			public void run() {
				HttpPost httpPost = new HttpPost(UrlManagerUtils
						.getCommentUrl(ingredient));
				httpPost.setHeader("Content-Type", "application/json");
				httpPost.addHeader("AUTHORIZATION",
						"Bearer " + PreferenceUtils.getToken());
				HttpClient httpClient = new DefaultHttpClient();
				try {
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("content", content);
					httpPost.setEntity(new StringEntity(jsonObject.toString()));
					HttpResponse httpResponse = httpClient.execute(httpPost);
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_CREATED) {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_COMMENT,
								new Caller<ICommentObserver>() {
									@Override
									public void call() {
										ob.ICommentObserver_sendcommend_success();
									}

								});
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
