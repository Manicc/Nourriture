package cn.edu.bjtu.svnteen.nourriture.mine;

import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.activity.MainActivity;
import cn.edu.bjtu.svnteen.nourriture.utils.JumperUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ProductUtils;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class MineFragment extends Fragment {
	protected static final int LOGIN_CODE = 100;
	private View mRootView;
	private Context mContext;
	private Button mLoginButton;
	/*private Intent mIntent=null;*/
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.activity_personal, null);
		mLoginButton = (Button)mRootView.findViewById(R.id.personal_login_button);
		initView();
		
		return mRootView;		
	}
	
	protected void initView() {
		// TODO Auto-generated method stub

		mLoginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            		switch (v.getId()) {
            		case R.id.personal_login_button:
            			JumperUtils.JumpToLogin();
            			/*mIntent=new Intent(MineFrangment.this,Login.this);*/
            		/*	loginFragment loginFragment = new loginFragment();
            			FragmentTransaction ft = MainActivity
            					.getInstance().getSupportFragmentManager().beginTransaction();
            			ft.show(loginFragment);*/
            			/*ft.commit();*/
            			
            			/*startActivityForResult(mIntent, LOGIN_CODE);*/
            			break;
                     }
            }
	    });	

    }
}
