package cn.edu.bjtu.svnteen.nourriture.core;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.observer.IObserverBase;
import android.os.Handler;

/**
 * @warn 禁止修改
 * @author Tans
 * 消息处理核心类
 */
public final class MessageManager {

	// 用于notify observers
	public abstract static class Caller<T extends IObserverBase> implements
			Runnable {
		// 使用者请覆盖Call函数
		public abstract void call();

		// 使用者请忽略此函数
		public void run() {
			if (!silence) {
				int processNotifyID = __id.ordinal();
				ArrayList<IObserverBase> list = obLists.get(processNotifyID);
				ProcessingNotifyStack.ProcessingItem processingItem = ProcessingNotifyStack
						.push(processNotifyID, list.size());
				while (processingItem.pos < processingItem.total) {
					@SuppressWarnings("unchecked")
					T item = (T) list.get(processingItem.pos);
					ob = item;
					call();
					++processingItem.pos;
				}
				ob = null;
				ProcessingNotifyStack.pop();
			}
			notifyFinish();
		}

		// 使用者严禁修改这几个变量
		protected T ob;
		public MessageID __id = MessageID.OBSERVER_ID_RESERVE;
		public boolean __sync = false; 

		protected final void notifyFinish() {
			if (__sync) {
				synchronized (this) {
					notify(); 
				}
			}
		}
	}

	// 用于run
	public abstract static class Runner extends Caller<IObserverBase> {

		// 异步通知有时候需要一个版本号，
		public Runner(int ver) {
			this();
			callVersion = ver;
		}

		// 使用者请覆盖Call函数
		public abstract void call();

		// 使用者请忽略此函�?
		public final void run() {
			call();
			notifyFinish();
		}

		protected int callVersion;

		public Runner() {
		}

	}

	// 单例
	public static MessageManager getInstance() {
		return instance;
	}

	// 注册消息响应
	public void attachMessage(final MessageID id, final IObserverBase ob) {
		ArrayList<IObserverBase> list = obLists.get(id.ordinal());
		if (!list.contains(ob)) {
			list.add(ob);
			ProcessingNotifyStack.doAttach(id.ordinal());
		} else {
		}
	}

	// 取消注册
	public void detachMessage(final MessageID id, final IObserverBase ob) {
		ArrayList<IObserverBase> list = obLists.get(id.ordinal());
		int size = list.size();
		for (int i = 0; i < size; ++i) {
			IObserverBase item = list.get(i);
			if (item == ob) {
				list.remove(ob);
				ProcessingNotifyStack.doDetach(id.ordinal(), i);
				return;
			}
		}
	}

	// 同步
	public <T extends IObserverBase> void syncNotify(final MessageID id,
			Caller<T> r) {
		r.__id = id;
		syncRunTargetHandler(mainThreadMsgHandler, r);
	}

	// 异步延时
	public <T extends IObserverBase> void asyncNotify(final MessageID id,
			final int delayMiliseconds, final Caller<T> r) {
		if (App.isExiting()) {
			return;
		}
		r.__id = id;
		asyncRunTargetHandler(mainThreadMsgHandler, delayMiliseconds, r);
	}

	// 异步0延时执行
	public <T extends IObserverBase> void asyncNotify(final MessageID id,
			final Caller<T> r) {
		if (App.isExiting()) {
			return;
		}
		r.__id = id;
		asyncRunTargetHandler(mainThreadMsgHandler, 0, r);
	}

	// 同步主线程执行
	public void syncRun(final Runner r) {
		syncRunTargetHandler(mainThreadMsgHandler, r);
	}

	// 异步主线程执行
	public void asyncRun(final int delayMiliseconds, final Runner r) {
		asyncRunTargetHandler(mainThreadMsgHandler, delayMiliseconds, r);
	}

	// 异步0延时主线程执行
	public void asyncRun(final Runner r) {
		asyncRunTargetHandler(mainThreadMsgHandler, r);
	}

	// 在某个handler里同步执行
	public <T extends IObserverBase> void syncRunTargetHandler(
			final Handler handler, final Caller<T> r) {
		if (handler.getLooper().getThread().getId() == Thread.currentThread()
				.getId()) {
			r.run();
		} else {
			r.__sync = true;
			try {
				synchronized (r) {
					handler.post(r);
					if (handler == mainThreadMsgHandler && App.isExiting()) {
					} else {
						r.wait(); 
					}
				}
				r.__sync = false;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	// 在某个handler里异步执行
	public <T extends IObserverBase> void asyncRunTargetHandler(
			final Handler handler, final int delayMiliseconds, final Caller<T> r) {
		handler.postDelayed(r, delayMiliseconds);
	}

	// 在某个handler里异步延时执行
	public void asyncRunTargetHandler(final Handler handler, final Runner r) {
		asyncRunTargetHandler(handler, 0, r);
	}

	public void silence() {
		silence = true;
	}

	MessageManager() {
	}

	static final MessageManager instance = new MessageManager();
	static final long mainThreadId = App.getMainThreadID();
	static final Handler mainThreadMsgHandler = App.getMainThreadHandler();
	static boolean silence;
	static ArrayList<ArrayList<IObserverBase>> obLists;
	static {
		obLists = new ArrayList<ArrayList<IObserverBase>>(
				MessageID.values().length);
		for (int i = 0; i < MessageID.values().length; ++i) {
			obLists.add(new ArrayList<IObserverBase>());
		}
	}

}
