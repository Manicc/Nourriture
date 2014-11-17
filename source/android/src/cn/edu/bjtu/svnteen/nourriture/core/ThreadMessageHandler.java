package cn.edu.bjtu.svnteen.nourriture.core;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

/**
 * @warn 禁止修改
 * @author Tans
 * 消息处理机制的核心类
 */
public final class ThreadMessageHandler {
	
	public Handler getHandler() {
		return handler;
	}
	
	private HandlerThread handlerThread = null;
	
	private Handler handler = null;
	
	public ThreadMessageHandler() {
		handlerThread = new HandlerThread("core.ThreadMessageHandler");
		handlerThread.start();
		handler = new Handler(handlerThread.getLooper());
	}
	
	public ThreadMessageHandler(Looper looper) {
		handler = new Handler(looper);
	}
}
