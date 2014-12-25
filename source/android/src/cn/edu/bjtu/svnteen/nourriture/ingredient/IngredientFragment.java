package cn.edu.bjtu.svnteen.nourriture.ingredient;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.adapter.IngredientListViewAdapter;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.IngredientDataModel;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.observer.IIngredientJsonObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.IngredientUtils;

public class IngredientFragment extends Fragment implements
		IIngredientJsonObserver {

	private View mRootView;
	private ListView mListView;
	private ArrayList<IngredientDataModel> mIngredientDataModelList;
	private IngredientListViewAdapter mListViewAdapter;
	
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		MessageManager.getInstance().attachMessage(
				MessageID.OBSERVER_INGREDIENT_JSON, this);// 在onAttach中注册消息
	}

	@Override
	public void onDetach() {
		super.onDetach();
		MessageManager.getInstance().detachMessage(
				MessageID.OBSERVER_INGREDIENT_JSON, this);// 在onDetach中解除消息
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.ingredient, container, false);
		mListView = (ListView) mRootView.findViewById(R.id.ingredient_listview);
		IngredientUtils.getIngredientsData();
		return mRootView;
	}

	@Override
	public void IIngredientJsonObserver_ALL(ArrayList<Ingredient> list) {
		mIngredientDataModelList = IngredientUtils
				.getConvertedIngredients(list);
		mListViewAdapter = new IngredientListViewAdapter(getActivity(),
				mIngredientDataModelList);
		mListView.setAdapter(mListViewAdapter);
		mListViewAdapter.notifyDataSetChanged();

	}
}
