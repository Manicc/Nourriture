package cn.edu.bjtu.svnteen.nourriture.utils;

import android.support.v4.app.Fragment;
import cn.edu.bjtu.svnteen.nourriture.fragment.FragmentControl;
import cn.edu.bjtu.svnteen.nourriture.fragment.MyFirstFragment;
import cn.edu.bjtu.svnteen.nourriture.fragment.ProductFragment;
import cn.edu.bjtu.svnteen.nourriture.fragment.TestFragment;

/**
 * @author Tans
 * 所有的fragment的跳转必须在此写方法
 */
public class JumperUtils {

	// eg: 跳转到第一个fragment
	public static void JumpToFirst() {
		Fragment topF = FragmentControl.getInstance().getTopFragment();
		if (topF != null && topF instanceof MyFirstFragment) {
			return;
		}
		if (FragmentControl.getInstance().getFragment(
				MyFirstFragment.class.getName()) != null) {
			FragmentControl.getInstance().naviFragment(
					MyFirstFragment.class.getName());
		} else {
			MyFirstFragment f = new MyFirstFragment();
			JumpToMain(f, MyFirstFragment.class.getName());
		}
	}

	// 跳转到ProductFragment
	public static void JumpToProduct() {
		Fragment topF = FragmentControl.getInstance().getTopFragment();
		if (topF != null && topF instanceof ProductFragment) {
			return;
		}
		if (FragmentControl.getInstance().getFragment(
				ProductFragment.class.getName()) != null) {
			FragmentControl.getInstance().naviFragment(
					ProductFragment.class.getName());
		} else {
			ProductFragment f = new ProductFragment();
			JumpToMain(f, ProductFragment.class.getName());
		}
	}
	
	
	
	// 跳转到ProductFragment
		public static void JumpToTest() {
			Fragment topF = FragmentControl.getInstance().getTopFragment();
			if (topF != null && topF instanceof TestFragment) {
				return;
			}
			if (FragmentControl.getInstance().getFragment(
					TestFragment.class.getName()) != null) {
				FragmentControl.getInstance().naviFragment(
						TestFragment.class.getName());
			} else {
				TestFragment f = new TestFragment();
				JumpToMain(f, TestFragment.class.getName());
			}
		}


	// 基本方法
	public static void JumpToMain(final Fragment fragment, final String tag) {
		FragmentControl.getInstance().showMainFrag(fragment, tag);
	}
}
