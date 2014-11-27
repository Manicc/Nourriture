package cn.edu.bjtu.svnteen.nourriture.observer;


public interface IAppObserver extends IObserverBase {

	// Service层初始化完毕，这之后service.Proxy接口才生�?
	void IAppObserver_InitFinished();

	// 启动画面显示完毕，系统保证发生在InitFinished之后
	void IAppObserver_WelcomePageDisappear();

	// 网络状�?发生变化。网络可用state为true, 网络为wifi时type为true
	void IAppObserver_NetworkStateChanged(boolean state, boolean isWifi);

	// 进入前台
	void IAppObserver_OnForground();

	// 转入后台
	void IAppObserver_OnBackground();

}
