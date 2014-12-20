package cn.edu.bjtu.svnteen.nourriture.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.utils.JumperUtils;

public class HomeFragment extends Fragment {

	private View mRootView;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.d("tanshuai","HomeFragemtn---> onCreateView");

		mRootView = inflater.inflate(R.layout.home, container, false);
		Button b = (Button) mRootView.findViewById(R.id.button);
		b.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				JumperUtils.JumpToTest();
				
			}
			
		});
		return mRootView;
	}
}
