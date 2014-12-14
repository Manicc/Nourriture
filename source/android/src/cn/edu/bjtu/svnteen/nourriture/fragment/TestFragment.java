package cn.edu.bjtu.svnteen.nourriture.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.svnteen.nourriture.R;

public class TestFragment extends BaseFragment {
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.testfragment, container, false);
		return view;
	}

}
