package cn.edu.bjtu.svnteen.nourriture.home;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.adapter.RecipesListViewAdapter;
import cn.edu.bjtu.svnteen.nourriture.adapter.SearchListViewAdapter;
import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;
import cn.edu.bjtu.svnteen.nourriture.bean.Search;
import cn.edu.bjtu.svnteen.nourriture.bean.SearchItem;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.observer.ISearchObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.JumperUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.SearchUtils;

public class HomeFragment extends Fragment implements ISearchObserver,
		OnClickListener {

	private Context mContext;
	private View mRootView;
	private ListView mRecipeListView;
	private RecipesListViewAdapter mAdapter;
	private ArrayList<Recipe> mRecipesArrayList;
	private ListView mSearchIngListView;
	private ListView mSearchProListView;
	private ListView mSearchRecListView;
	private SearchListViewAdapter mSearchIngAdapter;
	private SearchListViewAdapter mSearchProAdapter;
	private SearchListViewAdapter mSearchRecAdapter;

	private Button mIngBtn;
	private Button mProBtn;
	private Button mRecBtn;

	private LinearLayout mLinearLayout;
	private EditText mEditText;
	private Button mButton;

	private ArrayList<Ingredient> mIngredientList;
	private ArrayList<Product> mProductList;
	private ArrayList<Recipe> mRecipeList;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		MessageManager.getInstance().attachMessage(MessageID.OBSERVER_SEARCH,
				this);// 在onAttach中注册消息
	}

	@Override
	public void onDetach() {
		super.onDetach();
		MessageManager.getInstance().detachMessage(MessageID.OBSERVER_SEARCH,
				this);// 在onDetach中解除消息
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("tanshuai", "HomeFragemtn---> onCreateView");
		mRootView = inflater.inflate(R.layout.home, container, false);
		mContext = getActivity();
		mRecipeListView = (ListView) mRootView.findViewById(R.id.home_listview);
		mAdapter = new RecipesListViewAdapter(mContext, null);
		mRecipeListView.setAdapter(mAdapter);

		mLinearLayout = (LinearLayout) mRootView
				.findViewById(R.id.linearlayout);
		mSearchIngListView = (ListView) mRootView
				.findViewById(R.id.search_ing_listview);
		mSearchProListView = (ListView) mRootView
				.findViewById(R.id.search_pro_listview);
		mSearchRecListView = (ListView) mRootView
				.findViewById(R.id.search_rec_listview);
		mIngBtn = (Button) mRootView.findViewById(R.id.ingbtn);
		mProBtn = (Button) mRootView.findViewById(R.id.probtn);
		mRecBtn = (Button) mRootView.findViewById(R.id.recbtn);

		mEditText = (EditText) mRootView.findViewById(R.id.home_edittext);
		mIngBtn.setOnClickListener(this);
		mProBtn.setOnClickListener(this);
		mRecBtn.setOnClickListener(this);

		mSearchIngListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				JumperUtils.JumpToIngredientDetail(mIngredientList
						.get(position));
			}
		});

		mSearchProListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				JumperUtils.JumpToProductDetails(mProductList.get(position));
			}
		});
		mSearchRecListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				JumperUtils.JumpToRecipeDetail(mRecipeList.get(position));
			}
		});
		mButton = (Button) mRootView.findViewById(R.id.home_button);
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (TextUtils.isEmpty(mEditText.getText().toString())) {
					return;
				}
				InputMethodManager imm = (InputMethodManager) mContext
						.getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
				SearchUtils.getSearchResult(mEditText.getText().toString());// 开始搜索
			}
		});
		return mRootView;
	}

	@Override
	public void ISearchObserver_success(Search search) {
		ArrayList<SearchItem> ingList = new ArrayList<SearchItem>();
		ArrayList<SearchItem> proList = new ArrayList<SearchItem>();
		ArrayList<SearchItem> recList = new ArrayList<SearchItem>();

		mIngredientList = search.getIngredientList();
		mProductList = search.getProductList();
		mRecipeList = search.getRecipeList();

		for (int i = 0; i < search.getIngredientList().size(); i++) {
			SearchItem item = new SearchItem();
			item.setImageUrl(search.getIngredientList().get(i).getImageURL());
			item.setName(search.getIngredientList().get(i).getName());
			ingList.add(item);
		}
		for (int i = 0; i < search.getProductList().size(); i++) {
			SearchItem item = new SearchItem();
			item.setImageUrl(search.getProductList().get(i).getImageUrl());
			item.setName(search.getProductList().get(i).getName());
			proList.add(item);
		}
		for (int i = 0; i < search.getRecipeList().size(); i++) {
			SearchItem item = new SearchItem();
			item.setImageUrl(search.getRecipeList().get(i).getImageUrl());
			item.setName(search.getRecipeList().get(i).getName());
			recList.add(item);
		}

		mSearchIngAdapter = new SearchListViewAdapter(mContext, ingList);
		mSearchProAdapter = new SearchListViewAdapter(mContext, proList);
		mSearchRecAdapter = new SearchListViewAdapter(mContext, recList);

		mSearchIngListView.setAdapter(mSearchIngAdapter);
		mSearchProListView.setAdapter(mSearchProAdapter);
		mSearchRecListView.setAdapter(mSearchRecAdapter);
		mRecipeListView.setVisibility(View.GONE);
		mLinearLayout.setVisibility(View.VISIBLE);
		mSearchIngListView.setVisibility(View.VISIBLE);

	}

	@Override
	public void ISearchObserver_failed() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ingbtn:
			mSearchProListView.setVisibility(View.GONE);
			mSearchRecListView.setVisibility(View.GONE);
			mSearchIngListView.setVisibility(View.VISIBLE);
			break;
		case R.id.probtn:
			mSearchProListView.setVisibility(View.VISIBLE);
			mSearchRecListView.setVisibility(View.GONE);
			mSearchIngListView.setVisibility(View.GONE);
			break;
		case R.id.recbtn:
			mSearchProListView.setVisibility(View.GONE);
			mSearchRecListView.setVisibility(View.VISIBLE);
			mSearchIngListView.setVisibility(View.GONE);
			break;
		}
	}
}
