package cn.edu.bjtu.svnteen.nourriture.product;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.ProductUtils;

public class ProductFragment extends BaseFragment implements
		IProductJsonObserver {

	private View mRootView;
	private TextView mTextView;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		MessageManager.getInstance().attachMessage(
				MessageID.OBSERVER_PRODUCT_JSON, this);// 在onAttach中注册消息
	}

	@Override
	public void onDetach() {
		super.onDetach();
		MessageManager.getInstance().detachMessage(
				MessageID.OBSERVER_PRODUCT_JSON, this);// 在onDetach中解除消息
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.product, container, false);
		mTextView = (TextView) mRootView.findViewById(R.id.jsonTv);
		ProductUtils.getAllProducts();
		return mRootView;
	}

	@Override
	public void IProductJsonObserver_All(String json) {
		mTextView.setText(json);
	}

	@Override
	public void IProductJsonObserver_ID(String json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IProductJsonObserver_TagAndIngredient(String json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IProductJsonObserver_Failed() {
		mTextView.setText("json请求失败");
	}

}
