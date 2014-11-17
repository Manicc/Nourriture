package cn.edu.bjtu.svnteen.nourriture.fragment;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.uilib.SwipeBackFragment;
import cn.edu.bjtu.svnteen.nourriture.uilib.SwipeBackLayout;


/**
 * @warn 禁止修改
 * @author Tans
 * 所有fragment的父类
 */
public class BaseFragment extends SwipeBackFragment {

	final static String Tag = "BaseFragment";

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// 将皮肤层插入到现有layer
		ViewGroup skinlayer = (ViewGroup) LayoutInflater.from(getActivity())
				.inflate(R.layout.main_skin, null);
		SwipeBackLayout swipView = getSwipeBackLayout();
		ViewGroup childView = (ViewGroup) swipView.getChildAt(0);

		swipView.removeView(childView);
		skinlayer.addView(childView);
		swipView.setContentView(skinlayer);
		swipView.addView(skinlayer);
	}

	boolean bActive = false;

	public void Resume() {
	}

	public void Pause() {
	}

	@Override
	final public void onResume() {
		super.onResume();
		setSwipeBackEnable(true);
		if (!bActive
				&& ((FragmentControl.getInstance().getTopFragment() == this) || (FragmentControl
						.getInstance().isMainLayerShow()))) {
			bActive = true;
			Resume();
		}

	}

	@Override
	final public void onPause() {
		super.onPause();
		if (bActive) {
			bActive = false;
			Pause();
		}
	}

	// 返回true表示不向下传递消息了
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		return false;
	}

	@Override
	public void close() {
		if (FragmentControl.getInstance().getTopFragment() == this)
			FragmentControl.getInstance().closeFragment();
	}

}
