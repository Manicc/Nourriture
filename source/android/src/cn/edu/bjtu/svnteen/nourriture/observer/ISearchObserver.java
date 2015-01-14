package cn.edu.bjtu.svnteen.nourriture.observer;

import cn.edu.bjtu.svnteen.nourriture.bean.Search;

public interface ISearchObserver extends IObserverBase {

	void ISearchObserver_success(Search search);

	void ISearchObserver_failed();

}
