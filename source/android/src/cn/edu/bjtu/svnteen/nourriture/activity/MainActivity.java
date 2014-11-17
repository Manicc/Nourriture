package cn.edu.bjtu.svnteen.nourriture.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.Window;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.core.MessageID;
import cn.edu.bjtu.svnteen.nourriture.core.MessageManager;
import cn.edu.bjtu.svnteen.nourriture.fragment.FragmentControl;
import cn.edu.bjtu.svnteen.nourriture.fragment.MainFragment;
import cn.edu.bjtu.svnteen.nourriture.observer.IAppObserver;
import cn.edu.bjtu.svnteen.nourriture.utils.DeviceInfo;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

public class MainActivity extends FragmentActivity implements IAppObserver {

	private static MainActivity instance;

	public static MainActivity getInstance() {
		return instance;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		instance = this;
		init();

		MessageManager.getInstance()
				.attachMessage(MessageID.OBSERVER_APP, this);// 例子：怎么使用attachMessage方法
	}

	// 初始化相关数据
	private void init() {
		DeviceInfo.initScreenInfo(this);
		initMainFragment();
		ImageUtils.init(instance);
	}

	@Override
	protected void onDestroy() {
		if (instance == this) {
			instance = null;
		}
		super.onDestroy();
	}

	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			boolean ret = FragmentControl.getInstance().closeFragment();
			if (!ret) {
				moveTaskToBack(true);
			}
			return true;
		}
		return false;
	};

	/**
	 * 初始化主Fragment
	 */
	private void initMainFragment() {
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager
				.beginTransaction();
		MainFragment fragment = new MainFragment();
		fragmentTransaction.add(R.id.main_frame, fragment);
		fragmentTransaction.commit();

	}

	@Override
	public void IAppObserver_InitFinished() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_WelcomePageDisappear() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_NetworkStateChanged(boolean state, boolean isWifi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_OnForground() {
		// TODO Auto-generated method stub

	}

	@Override
	public void IAppObserver_OnBackground() {
		// TODO Auto-generated method stub

	}

}
