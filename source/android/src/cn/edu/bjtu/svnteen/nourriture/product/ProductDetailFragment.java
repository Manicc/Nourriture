package cn.edu.bjtu.svnteen.nourriture.product;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ProductUtils;

public class ProductDetailFragment extends BaseFragment implements
		IProductJsonObserver {

	private Context mContext;
	private View mRootView;
	private ImageView mImageView;
	private TextView mNameTextView;
	private TextView mDescTextView;
	public Product mProduct;

	
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
		mContext = getActivity();
		mRootView = inflater.inflate(R.layout.product_detail, null);
		mImageView = (ImageView) mRootView
				.findViewById(R.id.product_detail_imageview);
		mNameTextView = (TextView) mRootView
				.findViewById(R.id.product_detail_textview);
		mDescTextView = (TextView) mRootView
				.findViewById(R.id.product_detail_desc_textview);
		if (mProduct != null) {
			ProductUtils.getProductDetail(mProduct);
		}
		ImageUtils.loadImage(mProduct.getImageUrl(), mImageView);
		mNameTextView.setText(mProduct.getName());
		return mRootView;
	}

	@Override
	public void IProductJsonObserver_All(ArrayList<Product> products) {

	}

	@Override
	public void IProductJsonObserver_ID(Product product) {
		mDescTextView.setText(product.getDescription());
	}

	@Override
	public void IProductJsonObserver_TagAndIngredient(String json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IProductJsonObserver_Failed() {
		// TODO Auto-generated method stub

	}

}
