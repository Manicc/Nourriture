package cn.edu.bjtu.svnteen.nourriture.fragment;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);

		FragmentPagerAdapter adapter = new NourritureHomeAdapter(MainActivity
				.getInstance().getSupportFragmentManager());
		ViewPager pager = (ViewPager) rootView.findViewById(R.id.pager);
		pager.setAdapter(adapter);

		TabPageIndicator indicator = (TabPageIndicator) rootView
				.findViewById(R.id.indicator);
		indicator.setViewPager(pager);

		return rootView;
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
