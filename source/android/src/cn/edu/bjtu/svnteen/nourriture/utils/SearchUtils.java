package cn.edu.bjtu.svnteen.nourriture.utils;

import org.apache.http.Header;

import cn.edu.bjtu.svnteen.nourriture.bean.Search;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.observer.ISearchObserver;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class SearchUtils {

	public static void getSearchResult(String keyWord) {
		HttpUtils.get(UrlManagerUtils.getSearchUrl(keyWord),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int statusCode, Header[] arg1,
							byte[] result) {
						String json = new String(result);
						final Search search = JsonUtils.getSearch(json);
						if (search != null) {
							MessageManager.getInstance().syncNotify(
									MessageID.OBSERVER_SEARCH,
									new Caller<ISearchObserver>() {
										@Override
										public void call() {
											ob.ISearchObserver_success(search);
										}
									});
						}
					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						MessageManager.getInstance().syncNotify(
								MessageID.OBSERVER_SEARCH,
								new Caller<ISearchObserver>() {
									@Override
									public void call() {
										ob.ISearchObserver_failed();
									}
								});
					}
				});
	}
}
