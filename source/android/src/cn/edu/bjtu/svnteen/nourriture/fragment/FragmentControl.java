package cn.edu.bjtu.svnteen.nourriture.fragment;

import java.util.Stack;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.BackStackEntry;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Pair;
import android.view.KeyEvent;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.activity.MainActivity;

/**
 * @warn 禁止修改
 * @author Tans
 * fragment的控制逻辑类
 */
public class FragmentControl {

	static final String Tag = "FragmentControl";

	enum FragType {
		Type_Main_Flag
	};

	public static final String MAIN_FRAGMENT = "MainFragment";

	private static FragmentControl inst = new FragmentControl();

	public static FragmentControl getInstance() {
		return inst;
	}

	Stack<Pair<String, Fragment>> fragmentStack = new Stack<Pair<String, Fragment>>();

	public void realese() {
		fragmentStack.clear();
	}

	public boolean showMainFrag(Fragment fragment, String fname) {
		return showFragment(fragment, fname, FragType.Type_Main_Flag, 0);
	}

	boolean showFragment(Fragment fragment, String ftag, FragType type, int anim) {
		FragmentManager fragmentManager = MainActivity.getInstance()
				.getSupportFragmentManager();
		if (getFragment(ftag) != null) {
			StringBuilder sb = new StringBuilder();
			sb.append("Fragment:");
			sb.append(fragment.getClass().getName());
			sb.append(" Tag:");
			sb.append(ftag);
			sb.append(" has exist!");
			return false;
		}

		FragmentTransaction transaction = fragmentManager.beginTransaction();

		if (anim != 0)
			transaction.setCustomAnimations(anim, 0);

		transaction.add(R.id.main_frame, fragment, ftag);

		// 只保留当前层和其下一�?
		if (fragmentStack.size() > 1)
			transaction
					.hide(fragmentStack.get(fragmentStack.size() - 2).second);
		transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
		transaction.addToBackStack(ftag);
		pushFragment(fragment, ftag);
		transaction.commitAllowingStateLoss();
		return true;
	}

	void pushFragment(Fragment fragment, String tag) {
		if (isMainLayerShow()) {
		} else {
			Fragment topfrag = getTopFragment();
			if (topfrag != null) {
				topfrag.onPause();
			}
		}
		Pair<String, Fragment> temp = new Pair<String, Fragment>(tag, fragment);
		fragmentStack.push(temp);
	}

	public Fragment getFragment(String ftag) {
		for (int i = 0; i < fragmentStack.size(); i++) {
			Pair<String, Fragment> temp = fragmentStack.get(i);
			if (temp.first.equals(ftag))
				return temp.second;
		}
		return null;
	}

	// 跳转到某一个fragment并且关闭其上层的fragment
	public boolean naviFragment(String ftag) {
		boolean ret = false;
		try {
			if (MAIN_FRAGMENT.equals(ftag)) {
				clearBackStack();
			} else {
				ret = naviMainFragment(ftag);
			}
		} catch (Exception e) {
		}

		return ret;
	}

	private void clearBackStack() {
		FragmentManager fragmentManager = MainActivity.getInstance()
				.getSupportFragmentManager();
		int count = fragmentManager.getBackStackEntryCount();
		if (count > 0) {
			BackStackEntry entry = fragmentManager.getBackStackEntryAt(0);
			fragmentManager.popBackStack(entry.getId(),
					FragmentManager.POP_BACK_STACK_INCLUSIVE);
			popAllFragment();
		}
	}

	private boolean naviMainFragment(String ftag) {
		FragmentManager fragmentManager = MainActivity.getInstance()
				.getSupportFragmentManager();
		int count = fragmentManager.getBackStackEntryCount();
		for (int i = 0; i < count; i++) {
			BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
			if (entry != null && !TextUtils.isEmpty(ftag)
					&& entry.getName().contains(ftag)) {
				if (i < count - 1) {
					BackStackEntry topEntry = fragmentManager
							.getBackStackEntryAt(i + 1);
					fragmentManager.popBackStack(topEntry.getId(),
							FragmentManager.POP_BACK_STACK_INCLUSIVE);
					popFragment(fragmentStack.size() - i - 1);
				}
				return true;
			}
		}
		return false;
	}

	void popFragment(int count) {
		for (int i = 0; i < count; i++) {
			if (fragmentStack.size() > 0) {
				fragmentStack.pop();
			} else {
			}
		}
		if (isMainLayerShow()) {
		} else {
			Fragment topfrag = getTopFragment();
			if (topfrag != null) {
				topfrag.onResume();
			}
		}
	}

	void popAllFragment() {
		fragmentStack.clear();
	}

	public boolean closeFragment() {
		try {
			FragmentManager fragmentManager = MainActivity.getInstance()
					.getSupportFragmentManager();
			int count = fragmentManager.getBackStackEntryCount();
			if (count > 0) {
				BackStackEntry entry = fragmentManager
						.getBackStackEntryAt(count - 1);

				fragmentManager.popBackStack(entry.getId(),
						FragmentManager.POP_BACK_STACK_INCLUSIVE);
				popFragment(1);
				return true;
			}
		} catch (Exception e) {
		}

		return false;
	}

	// 关闭包括ftag的fragment之内的其上所有层
	public boolean closeFragmentUp(String ftag) {
		try {
			FragmentManager fragmentManager = MainActivity.getInstance()
					.getSupportFragmentManager();
			int count = fragmentManager.getBackStackEntryCount();
			for (int i = 0; i < count; i++) {
				BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
				if (entry != null && !TextUtils.isEmpty(ftag)
						&& entry.getName().contains(ftag)) {
					if (i < count - 1) {
						BackStackEntry topEntry = fragmentManager
								.getBackStackEntryAt(i);
						fragmentManager.popBackStack(topEntry.getId(),
								FragmentManager.POP_BACK_STACK_INCLUSIVE);
						popFragment(fragmentStack.size() - i);
					}
					return true;
				}
			}
		} catch (Exception e) {
		}

		return false;
	}

	public boolean isMainLayerShow() {
		return (getTopFragmentName().equals(MAIN_FRAGMENT)) ? true : false;
	}

	public Fragment getTopFragment() {
		if (fragmentStack.size() > 0)
			return fragmentStack.get(fragmentStack.size() - 1).second;
		else {
			return null;
		}
	}

	public String getTopFragmentName() {
		if (fragmentStack.size() > 0)
			return fragmentStack.get(fragmentStack.size() - 1).first;
		else
			return MAIN_FRAGMENT;
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Fragment topfrag = getTopFragment();
		boolean ret = false;
		if (topfrag != null && topfrag instanceof BaseFragment) {
			ret = ((BaseFragment) topfrag).onKeyDown(keyCode, event);
		}
		return ret;
	}

}