package cn.edu.bjtu.svnteen.nourriture.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.FragmentControl;
import cn.edu.bjtu.svnteen.nourriture.fragment.MainFragment;
import cn.edu.bjtu.svnteen.nourriture.home.HomeFragment;
import cn.edu.bjtu.svnteen.nourriture.ingredient.IngredientFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.IAppObserver;
import cn.edu.bjtu.svnteen.nourriture.product.ProductFragment;
import cn.edu.bjtu.svnteen.nourriture.recipes.RecipeFragment;
import cn.edu.bjtu.svnteen.nourriture.utils.DeviceInfo;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;
import cn.edu.bjtu.svnteen.nourriture.viewpagerindicator.TabPageIndicator;

public class MainActivity extends FragmentActivity implements IAppObserver {

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
		mViewPager.setOffscreenPageLimit(4);

		mPageIndicator.setViewPager(mViewPager);

		MessageManager.getInstance()
				.attachMessage(MessageID.OBSERVER_APP, this);// 例子：怎么使用attachMessage方法
		Log.d("tanshuai", "MainActivity---> onCreate");
	}

	@Override
	protected void onResume() {

		Log.d("tanshuai", "MainActivity---> onResume");
		super.onResume();
	}

	// 初始化相关数据
	private void init() {
		DeviceInfo.initScreenInfo(this);
		// initMainFragment();
		ImageUtils.init(instance);
	}

	@Override
	protected void onDestroy() {
		if (instance == this) {
			instance = null;
		}
		Log.d("tanshuai", "MainActivity---> onDestroy");
		super.onDestroy();
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

	/**
	 * 初始化主Fragment
	 */
	private void initMainFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		MainFragment fragment = new MainFragment();
		fragmentTransaction.add(R.id.main_frame, fragment);
		fragmentTransaction.commit();

	}

	@Override
	public void IAppObserver_InitFinished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_WelcomePageDisappear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_NetworkStateChanged(boolean state, boolean isWifi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_OnForground() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_OnBackground() {
		// TODO Auto-generated method stub

	}

}
