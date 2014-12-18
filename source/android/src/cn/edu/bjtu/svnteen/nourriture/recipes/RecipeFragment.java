package cn.edu.bjtu.svnteen.nourriture.recipes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;

public class RecipeFragment extends BaseFragment{
	
	private View mRootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.home, container, false);
		return mRootView;
	}
}
