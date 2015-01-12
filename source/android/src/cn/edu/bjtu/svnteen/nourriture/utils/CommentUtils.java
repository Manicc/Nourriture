package cn.edu.bjtu.svnteen.nourriture.utils;

import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpResponseHandler;

import cn.edu.bjtu.svnteen.nourriture.bean.Comment;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.observer.ICommentObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;

public class CommentUtils {

	public static void getComment(final Ingredient ingredient) {

		HttpUtils.get(UrlManagerUtils.getCommentUrl(ingredient),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] headers,
							byte[] response) {
						String result = new String(response);
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
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_COMMENT,
								new Caller<ICommentObserver>() {
									@Override
									public void call() {
										ob.ICommentObserver_failed();
									}

								});
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
