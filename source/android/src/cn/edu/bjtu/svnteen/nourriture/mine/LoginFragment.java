package cn.edu.bjtu.svnteen.nourriture.mine;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.activity.MainActivity;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager.Caller;
import cn.edu.bjtu.svnteen.nourriture.fragment.BaseFragment;
import cn.edu.bjtu.svnteen.nourriture.fragment.FragmentControl;
import cn.edu.bjtu.svnteen.nourriture.observer.ILoginObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.LoginUtils;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool;
import cn.edu.bjtu.svnteen.nourriture.utils.StThreadPool.JobType;

public class LoginFragment extends BaseFragment implements OnClickListener {
	private View mRootView;
	private View mLoadingView;
	private EditText mNameEditText;
	private EditText mPasswordEditText;
	private Button mLoginButton;
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mRootView = inflater.inflate(R.layout.login, null);
		mLoadingView = mRootView.findViewById(R.id.login_status);
		mNameEditText = (EditText) mRootView.findViewById(R.id.name);
		mPasswordEditText = (EditText) mRootView.findViewById(R.id.password);
		mLoginButton = (Button) mRootView.findViewById(R.id.login);
		mLoginButton.setOnClickListener(this);
		return mRootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login:
			final String name = mNameEditText.getText().toString();
			if (TextUtils.isEmpty(name)) {
				Toast.makeText(getActivity(), "用户名不能为空", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			final String password = mPasswordEditText.getText().toString();
			if (TextUtils.isEmpty(password)) {
				Toast.makeText(getActivity(), "密码不能为空", Toast.LENGTH_SHORT)
						.show();
				return;
			}

			StThreadPool.runThread(JobType.NET, new Runnable() {

				@Override
				public void run() {
					if (LoginUtils.login(name, password) == 1) {
						MessageManager.getInstance().asyncNotify(
								MessageID.OBSERVER_LOGIN,
								new Caller<ILoginObserver>() {

									@Override
									public void call() {
										ob.ILoginObserver_successed();
									}
								});
						MainActivity.getInstance().runOnUiThread(
								new Runnable() {

									@Override
									public void run() {
										FragmentControl.getInstance()
												.closeFragment();
									}
								});
					} else {
						MainActivity.getInstance().runOnUiThread(
								new Runnable() {
									@Override
									public void run() {
										mLoadingView.setVisibility(View.GONE);
										Toast.makeText(mContext, "用户名或密码错误",
												Toast.LENGTH_SHORT).show();
									}
								});
					}

				}
			});
			mLoadingView.setVisibility(View.VISIBLE);
			break;

		default:
			break;
		}
	}
}
