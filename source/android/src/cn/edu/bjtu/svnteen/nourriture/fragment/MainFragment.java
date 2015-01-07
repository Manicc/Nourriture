package cn.edu.bjtu.svnteen.nourriture.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.activity.MainActivity;
import cn.edu.bjtu.svnteen.nourriture.home.HomeFragment;
import cn.edu.bjtu.svnteen.nourriture.ingredient.IngredientFragment;
import cn.edu.bjtu.svnteen.nourriture.product.ProductFragment;
import cn.edu.bjtu.svnteen.nourriture.recipes.RecipeFragment;
import cn.edu.bjtu.svnteen.nourriture.viewpagerindicator.TabPageIndicator;

/**
 * MainFragment
 * @author Tans
 */
public class MainFragment extends Fragment {

	private View mRootView;
	private ViewPager mViewPager;
	private TabPageIndicator mPageIndicator;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("tanshuai","MainFragemtn---> onCreateView");
		mRootView = inflater.inflate(R.layout.fragment_main, container, false);
		mViewPager = (ViewPager) mRootView.findViewById(R.id.pager);
		mPageIndicator = (TabPageIndicator) mRootView
				.findViewById(R.id.indicator);

		FragmentPagerAdapter adapter = new NourritureHomeAdapter(MainActivity
				.getInstance().getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		mViewPager.setOffscreenPageLimit(4);

		mPageIndicator.setViewPager(mViewPager);
		return mRootView;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("tanshuai","MainFragemtn---> onCreate");
	}
	@Override
	public void onResume() {
		Log.d("tanshuai","MainFragemtn---> onResume");
		super.onResume();
	}
	@Override
	public void onDestroy() {
		Log.d("tanshuai","MainFragemtn---> onDestroy");
		super.onDestroy();
	}
	@Override
	public void onDestroyView() {
		Log.d("tanshuai","MainFragemtn---> onDestroyView");
		super.onDestroyView();
	}

	class NourritureHomeAdapter extends FragmentPagerAdapter {

		private List<Fragment> mList;
		private final String[] mTab;

		public NourritureHomeAdapter(FragmentManager fm) {
			super(fm);

			mTab = new String[] { "首页", "食材", "产品", "食谱" };
			mList = new ArrayList<Fragment>();

			HomeFragment homeFragment = new HomeFragment();
			IngredientFragment ingredientFragment = new IngredientFragment();
			ProductFragment productFragment = new ProductFragment();
			RecipeFragment recipeFragment = new RecipeFragment();

			mList.add(homeFragment);
			mList.add(ingredientFragment);
			mList.add(productFragment);
			mList.add(recipeFragment);
		}

		@Override
		public Fragment getItem(int position) {
			return mList.get(position);
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return mTab[position];
		}

		@Override
		public int getCount() {
			return mTab.length;
		}

	}

}
