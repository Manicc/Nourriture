package cn.edu.bjtu.svnteen.nourriture.product;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Gallery;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ProductUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ScreenUtils;

public class ProductDetailFragment extends BaseFragment implements
		IProductJsonObserver {

	private Context mContext;
	private View mRootView;
	private ImageView mImageView;
	private TextView mNameTextView;
	private TextView mDescTextView;
	private TextView mIngredientTextView;
	private LinearLayout mLinearLayout;
	public static Product mProduct;

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
		mRootView = inflater.inflate(R.layout.product_detail, container, false);
		mImageView = (ImageView) mRootView
				.findViewById(R.id.product_detail_imageview);
		mNameTextView = (TextView) mRootView
				.findViewById(R.id.product_detail_textview);
		mDescTextView = (TextView) mRootView
				.findViewById(R.id.product_detail_desc_textview);
		mLinearLayout = (LinearLayout) mRootView
				.findViewById(R.id.linearlayout);
		mIngredientTextView = (TextView) mRootView
				.findViewById(R.id.ingredient);
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
	}

	@Override
	public void IProductJsonObserver_TagAndIngredient(String json) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IProductJsonObserver_Failed() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IProductJsonObserver_Detail(Product product) {
		mDescTextView.setText(product.getDescription());
		ProductUtils.getIngredient(product);
	}

	@Override
	public void IProductJsonObserver_Detail_ingredients(Product product) {
		ArrayList<Ingredient> list = product.getIngredientArrayList();
		StringBuilder builder = new StringBuilder("成分:  ");
		for (Ingredient ingredient : list) {
			builder.append(ingredient.getName());
			builder.append(" ");
			ImageView v = new ImageView(mContext);
			v.setLayoutParams(new LinearLayout.LayoutParams(ScreenUtils.dip2px(
					mContext, 100), ScreenUtils.dip2px(mContext, 100)));
			ImageUtils.loadImage(ingredient.getImageURL(), v);
			mLinearLayout.addView(v);
		}
		mIngredientTextView.setText(builder.toString());
	}

	@Override
	public void IProductJsonObserver_Detail_nutrition(Product product) {
		// TODO Auto-generated method stub

	}

}
