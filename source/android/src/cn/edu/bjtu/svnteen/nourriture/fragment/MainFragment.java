package cn.edu.bjtu.svnteen.nourriture.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.utils.JumperUtils;

public class MainFragment extends Fragment implements OnClickListener {

	private Button mButton;
	private Button mProductButton;
	private Button mTestBtn;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container,
				false);
		mButton = (Button) rootView.findViewById(R.id.button);
		mTestBtn = (Button) rootView.findViewById(R.id.test);
		mTestBtn.setOnClickListener(this);
		mButton.setOnClickListener(this);
		mProductButton = (Button) rootView.findViewById(R.id.product_button);
		mProductButton.setOnClickListener(this);
		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.test:
			JumperUtils.JumpToTest();
			Toast.makeText(getActivity(), "test", Toast.LENGTH_SHORT).show();
			break;
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
