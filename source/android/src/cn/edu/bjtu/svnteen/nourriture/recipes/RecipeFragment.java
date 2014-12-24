package cn.edu.bjtu.svnteen.nourriture.recipes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TabHost;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.adapter.RecipeGridViewAdapter;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.observer.IRecipeJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.RecipeUtils;

public class RecipeFragment extends Fragment implements IRecipeJsonObserver {

	private Context mContext;
	private View mRootView;
	private GridView mGrdiView;
	private RecipeGridViewAdapter mGridViewAdapter;
	private ArrayList<Recipe> mRecipeList;

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
		mRootView = inflater.inflate(R.layout.recipe, null);
		mGrdiView = (GridView) mRootView.findViewById(R.id.gridview);
		RecipeUtils.getRecipes();
		return mRootView;
	}

	@Override
	public void IRecipeJsonObserver_All(ArrayList<Recipe> list) {
		mRecipeList = list;
		mGridViewAdapter = new RecipeGridViewAdapter(mContext, mRecipeList);
		mGrdiView.setAdapter(mGridViewAdapter);
		mGridViewAdapter.notifyDataSetChanged();
	}
}
