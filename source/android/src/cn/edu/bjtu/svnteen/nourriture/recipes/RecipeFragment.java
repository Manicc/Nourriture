package cn.edu.bjtu.svnteen.nourriture.recipes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.svnteen.nourriture.R;

public class RecipeFragment extends Fragment{
	
	private View mRootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.home, container, false);
		return mRootView;
	}
}
