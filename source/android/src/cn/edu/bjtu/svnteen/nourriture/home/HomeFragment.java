package cn.edu.bjtu.svnteen.nourriture.home;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.adapter.RecipesListViewAdapter;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public class HomeFragment extends Fragment {

	private Context mContext;
	private View mRootView;
	private ListView mRecipeListView;
	private RecipesListViewAdapter mAdapter;
	private ArrayList<Recipe> mRecipesArrayList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("tanshuai", "HomeFragemtn---> onCreateView");
		mRootView = inflater.inflate(R.layout.home, container, false);
		mContext = getActivity();
		mRecipeListView = (ListView) mRootView.findViewById(R.id.home_listview);
		mAdapter = new RecipesListViewAdapter(mContext, null);
		mRecipeListView.setAdapter(mAdapter);
		return mRootView;
	}
}
