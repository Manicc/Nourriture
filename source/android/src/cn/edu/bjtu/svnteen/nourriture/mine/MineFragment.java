package cn.edu.bjtu.svnteen.nourriture.mine;

import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.activity.MainActivity;
import cn.edu.bjtu.svnteen.nourriture.bean.Favorite;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.observer.ILoginObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.FavoriteUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.JumperUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.PreferenceUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.ProductUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;
import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MineFragment extends Fragment implements ILoginObserver {
	protected static final int LOGIN_CODE = 100;
	private View mRootView;
	private Context mContext;
	private Button mLoginButton;
	private Button mLogoutButton;
	private LinearLayout mLoginView;
	private RelativeLayout mLoginAfterview;
	private TextView mUserNameTextView;

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		MessageManager.getInstance().attachMessage(MessageID.OBSERVER_LOGIN,
				this);// 在onAttach中注册消息

	}

	@Override
	public void onDetach() {
		super.onDetach();
		MessageManager.getInstance().attachMessage(MessageID.OBSERVER_LOGIN,
				this);// 在onAttach中注册消息

	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = inflater.inflate(R.layout.activity_personal, null);
		mLoginButton = (Button) mRootView
				.findViewById(R.id.personal_login_button);
		mLogoutButton = (Button) mRootView.findViewById(R.id.personal_exit);
		mLoginView = (LinearLayout) mRootView.findViewById(R.id.login);
		mLoginAfterview = (RelativeLayout) mRootView
				.findViewById(R.id.personal);
		mUserNameTextView = (TextView) mRootView.findViewById(R.id.username);
		initView();

		return mRootView;
	}

	protected void initView() {

		if (PreferenceUtils.getLogin()) {
			ILoginObserver_successed();
		}

		mLogoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PreferenceUtils.clearAllData();
				mLoginView.setVisibility(View.VISIBLE);
				mLogoutButton.setVisibility(View.GONE);
				mLoginAfterview.setVisibility(View.GONE);
			}
		});
		mLoginButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				switch (v.getId()) {
				case R.id.personal_login_button:
					JumperUtils.JumpToLogin();
					/* mIntent=new Intent(MineFrangment.this,Login.this); */
					/*
					 * loginFragment loginFragment = new loginFragment();
					 * FragmentTransaction ft = MainActivity
					 * .getInstance().getSupportFragmentManager
					 * ().beginTransaction(); ft.show(loginFragment);
					 */
					/* ft.commit(); */

					/* startActivityForResult(mIntent, LOGIN_CODE); */
					break;
				}
			}
		});

	}

	@Override
	public void ILoginObserver_successed() {
		mLoginView.setVisibility(View.GONE);
		mLogoutButton.setVisibility(View.VISIBLE);
		mLoginAfterview.setVisibility(View.VISIBLE);
		mUserNameTextView.setText(PreferenceUtils.getUserName());
		StThreadPool.runThread(JobType.NET, new Runnable() {
			
			@Override
			public void run() {
				Favorite favoriteResult = FavoriteUtils.getFavorites();
				
			}
		});
		int a;
	}

	@Override
	public void ILoginObserver_failed() {
		// TODO Auto-generated method stub

	}
}
