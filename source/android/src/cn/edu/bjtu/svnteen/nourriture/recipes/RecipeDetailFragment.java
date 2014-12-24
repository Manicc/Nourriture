package cn.edu.bjtu.svnteen.nourriture.recipes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.IRecipeJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.RecipeUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ScreenUtils;

public class RecipeDetailFragment extends BaseFragment implements IRecipeJsonObserver{

	private Context mContext;
	private View mRootView;
	private ImageView mImageView;
	private TextView mNameTextView;
	private TextView mProcessTextView;
	private TextView mIngredientTextView;
	private LinearLayout mLinearLayout;
	public static Recipe mRecipe;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		MessageManager.getInstance().attachMessage(
				MessageID.OBSERVER_RECIPE_JSON, this);// 在onAttach中注册消息
	}

	@Override
	public void onDetach() {
		super.onDetach();
		MessageManager.getInstance().detachMessage(
				MessageID.OBSERVER_RECIPE_JSON, this);// 在onDetach中解除消息
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mRootView = inflater.inflate(R.layout.recipe_detail, null);
		mNameTextView = (TextView) mRootView
				.findViewById(R.id.recipe_detail_textview);
		mProcessTextView = (TextView) mRootView
				.findViewById(R.id.recipe_process_textview);
		mImageView = (ImageView) mRootView
				.findViewById(R.id.recipe_detail_imageview);
		mIngredientTextView = (TextView) mRootView
				.findViewById(R.id.ingredient);
		mLinearLayout = (LinearLayout) mRootView
				.findViewById(R.id.linearlayout);
		mNameTextView.setText(mRecipe.getName());
		mProcessTextView.setText(mRecipe.getProcessing());
		if (!TextUtils.isEmpty(mRecipe.getImageUrl())) {
			ImageUtils.loadImage(mRecipe.getImageUrl(), mImageView);
		}
		RecipeUtils.getIngredient(mRecipe);
		
		return mRootView;
	}

	@Override
	public void IRecipeJsonObserver_All(ArrayList<Recipe> list) {
		
	}

	@Override
	public void IRecipeJsonObserver_Detail_ingredients(Recipe recipe) {
		ArrayList<Ingredient> list = recipe.getIngredientList();
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

}
