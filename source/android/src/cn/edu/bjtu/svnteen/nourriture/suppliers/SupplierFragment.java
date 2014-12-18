package cn.edu.bjtu.svnteen.nourriture.suppliers;

import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SupplierFragment extends BaseFragment {
	private View mRootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mRootView = inflater.inflate(R.layout.home, container, false);
		return mRootView;
	}

}
