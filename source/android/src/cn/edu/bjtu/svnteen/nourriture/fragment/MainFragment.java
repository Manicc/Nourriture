package cn.edu.bjtu.svnteen.nourriture.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.utils.JumperUtils;

public class MainFragment extends Fragment implements OnClickListener {

	private Button mButton;
	private Button mProductButton;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		mButton = (Button) rootView.findViewById(R.id.button);
		mButton.setOnClickListener(this);
		mProductButton = (Button) rootView.findViewById(R.id.product_button);
		mProductButton.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.button:
			JumperUtils.JumpToFirst();
			break;
		case R.id.product_button:
			JumperUtils.JumpToProduct();
			break;

		default:
			break;
		}

	}

}
