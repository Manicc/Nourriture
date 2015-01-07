package cn.edu.bjtu.svnteen.nourriture.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.fragment.FragmentControl;
import cn.edu.bjtu.svnteen.nourriture.home.HomeFragment;
import cn.edu.bjtu.svnteen.nourriture.ingredient.IngredientFragment;
import cn.edu.bjtu.svnteen.nourriture.mine.MineFragment;
import cn.edu.bjtu.svnteen.nourriture.product.ProductFragment;
import cn.edu.bjtu.svnteen.nourriture.recipes.RecipeFragment;
import cn.edu.bjtu.svnteen.nourriture.utils.DeviceInfo;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.PreferenceUtils;
import cn.edu.bjtu.svnteen.nourriture.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private TabPageIndicator mPageIndicator;

	private static MainActivity instance;

	public static MainActivity getInstance() {
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN); // 设置全屏
		setContentView(R.layout.activity_main);
		instance = this;
		init();

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mPageIndicator = (TabPageIndicator) findViewById(R.id.indicator);

		FragmentPagerAdapter adapter = new NourritureHomeAdapter(MainActivity
				.getInstance().getSupportFragmentManager());
		mViewPager.setAdapter(adapter);
		mViewPager.setOffscreenPageLimit(5);

		mPageIndicator.setViewPager(mViewPager);
		mPageIndicator.setCurrentItem(1);
	}

	// 初始化相关数据
	private void init() {
		DeviceInfo.initScreenInfo(this);
		ImageUtils.init(instance);
		PreferenceUtils.init(this);
	}

	@Override
	protected void onDestroy() {
		if (instance == this) {
			instance = null;
		}
		super.onDestroy();
	}

	class NourritureHomeAdapter extends FragmentPagerAdapter {

		private List<Fragment> mList;
		private final String[] mTab;

		public NourritureHomeAdapter(FragmentManager fm) {
			super(fm);

			mTab = new String[] { "首页", "食材", "产品", "食谱", "我的" };
			mList = new ArrayList<Fragment>();

			HomeFragment homeFragment = new HomeFragment();
			IngredientFragment ingredientFragment = new IngredientFragment();
			ProductFragment productFragment = new ProductFragment();
			RecipeFragment recipeFragment = new RecipeFragment();
			MineFragment mineFragment = new MineFragment();

			mList.add(homeFragment);
			mList.add(ingredientFragment);
			mList.add(productFragment);
			mList.add(recipeFragment);
			mList.add(mineFragment);
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

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			boolean ret = FragmentControl.getInstance().closeFragment();
			if (!ret) {
				moveTaskToBack(true);
			}
			return true;
		}
		return false;
	};

}
