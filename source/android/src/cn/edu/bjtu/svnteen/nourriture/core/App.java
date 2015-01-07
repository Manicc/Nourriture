package cn.edu.bjtu.svnteen.nourriture.core;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import cn.edu.bjtu.svnteen.nourriture.observer.IAppObserver;

/**
 * @warn 禁止修改
 * @author Tans
 * 项目的Application类
 */
public final class App extends Application {
	
	private String token;

	public static App getInstance() {
		return instance;
	}

	public static Handler getMainThreadHandler() {
		return mainThreadHandler;
	}

	public static long getMainThreadID() {
		return mainThreadID;
	}
	
	public static boolean isMainProgress() {
		return mainProgress;
	}
	
	public static boolean isExiting() {
		return isExiting;
	}
	
	public static boolean isInited() {
		return inited;
	}
	
	public static boolean isRuning() {
		if (isExiting) {
			return false;
		}
		
		return inited;
	}

	public static void exitApp() {
		if (prepareExisting) {
			return;
		}
		
		prepareExisting = true;
		MessageManager.getInstance().syncNotify(MessageID.OBSERVER_APP,
				new MessageManager.Caller<IAppObserver>() {
					public void call() {
						try {
//							ob.IAppObserver_PrepareExitApp();
						} catch (Throwable e) {
						}
					}
				});
		// 异步一下，给已经发出还没执行的异步零延时observer一个执行的机会
		MessageManager.getInstance().asyncRun(new MessageManager.Runner() {
			@Override
			public void call() {
				isExiting = true; // 从此不能再发通知了
				MessageManager.getInstance().asyncRun(new MessageManager.Runner() {
					@Override
					public void call() {
						MessageManager.getInstance().silence();// 从此延时通知直接丢掉不执行了
						MessageManager.getInstance().asyncRun(500, new MessageManager.Runner() {
							@Override
							public void call() {
								android.os.Process.killProcess(android.os.Process.myPid());
								System.exit(0);
							}
						});
					}
				});
			}
		});
	}

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this; // 请忽略findbugs提示
	}
	

    @Override
	public void onLowMemory() {
		MessageManager.getInstance().syncNotify(MessageID.OBSERVER_APP, new MessageManager.Caller<IAppObserver>() {
			@Override
			public void call() {
//				ob.IAppObserver_OnLowMemory();
			}
		});
		super.onLowMemory();
		System.gc();
	}
	
	public void init(final Activity currentActivity, boolean isMainProcess) {
		inited = true;
		mainProgress = isMainProcess;
	}

	private static App		instance;
	private static boolean inited;
	private static Handler	mainThreadHandler	= new Handler();
	private static long		mainThreadID		= Thread.currentThread().getId();
	private static boolean prepareExisting;
	private static volatile boolean 	isExiting;
	private static boolean mainProgress;

}
