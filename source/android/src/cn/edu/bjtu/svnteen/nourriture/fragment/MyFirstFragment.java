package cn.edu.bjtu.svnteen.nourriture.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.edu.bjtu.svnteen.nourriture.R;
import cn.edu.bjtu.svnteen.nourriture.utils.ImageUtils;

public class MyFirstFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.myfirstfragment, container, false);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
		ImageUtils.loadImage(
				"http://www.bjtu.edu.cn/images/siyuanblue/fla.jpg", imageView);
		MyThread myThread = new MyThread();
		myThread.start();
		new Thread(){
			public void run() {
				Log.d("tanshuai", "run method!: 主线程zhijienew："
						+ Thread.currentThread().getId());
			};
		}.start();
		Log.d("tanshuai", "run method!: 主线程id："
				+ Thread.currentThread().getId());
		Log.d("tanshuai", "run method!: 子线程id：" + myThread.getId());
		try {
			for (int i = 0; i < 100; i++) {
				// Thread.currentThread().sleep(3000);
				System.out.println("run method!-->sendEmptyMessage   :"
						+ myThread.m.sendEmptyMessageDelayed(0, 0));
//				System.out.println("run method!-->sendEmptyMessage   :"
//						+ myThread.m.sendEmptyMessageDelayed(0, 0));
//				System.out.println("run method!-->sendEmptyMessage   :"
//						+ myThread.m.sendEmptyMessageDelayed(0, 0));
//				System.out.println("run method!-->sendEmptyMessage   :"
//						+ myThread.m.sendEmptyMessageDelayed(0, 0));
//				Thread.currentThread().sleep(5000);
//				Log.d("tanshuai", "run method!: 子线程状态:"
//						+ myThread.getState().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// while (true) {

		// }

		return view;
	}
}

class MyThread extends Thread {
	Handler m = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.d("tanshuai", "run method! handlemssage");
		};
	};

	@Override
	public void run() {
		Looper.prepare();
		Log.d("tanshuai", "run method!");
		Looper.loop();

	}
}