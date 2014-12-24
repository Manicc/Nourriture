package cn.edu.bjtu.svnteen.nourriture.utils;

import android.support.v4.app.Fragment;
import cn.edu.bjtu.svnteen.nourriture.bean.Product;
import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;
import cn.edu.bjtu.svnteen.nourriture.fragment.FragmentControl;
import cn.edu.bjtu.svnteen.nourriture.fragment.MyFirstFragment;
import cn.edu.bjtu.svnteen.nourriture.fragment.TestFragment;
import cn.edu.bjtu.svnteen.nourriture.product.ProductDetailFragment;
import cn.edu.bjtu.svnteen.nourriture.product.ProductFragment;
import cn.edu.bjtu.svnteen.nourriture.recipes.RecipeDetailFragment;

/**
 * @author Tans 所有的fragment的跳转必须在此写方法
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

	// 跳转到ProductDetailFragment
	public static void JumpToProductDetails(Product product) {
		Fragment topF = FragmentControl.getInstance().getTopFragment();
		if (topF != null && topF instanceof ProductDetailFragment) {
			return;
		}
		if (FragmentControl.getInstance().getFragment(
				ProductDetailFragment.class.getName()) != null) {
			FragmentControl.getInstance().naviFragment(
					ProductDetailFragment.class.getName());
		} else {
			ProductDetailFragment f = new ProductDetailFragment();
			ProductDetailFragment.mProduct = product;
			JumpToMain(f, ProductDetailFragment.class.getName());
		}
	}

	// 跳转到RecipeDetailFragment
	public static void JumpToRecipeDetail(Recipe recipe) {
		Fragment topF = FragmentControl.getInstance().getTopFragment();
		if(topF!=null&&topF instanceof RecipeDetailFragment){
			return;
		}
		if(FragmentControl.getInstance().getFragment(RecipeDetailFragment.class.getName()) !=null){
			FragmentControl.getInstance().naviFragment(RecipeDetailFragment.class.getName());
		}else{
			RecipeDetailFragment f = new RecipeDetailFragment();
			RecipeDetailFragment.mRecipe = recipe;
			JumpToMain(f, RecipeDetailFragment.class.getName());
		}
	}

	// 基本方法
	public static void JumpToMain(final Fragment fragment, final String tag) {
		FragmentControl.getInstance().showMainFrag(fragment, tag);
	}
}
