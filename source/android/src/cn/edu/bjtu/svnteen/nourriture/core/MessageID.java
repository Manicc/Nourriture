package cn.edu.bjtu.svnteen.nourriture.core;

import cn.edu.bjtu.svnteen.nourriture.observer.IAppObserver;
import cn.edu.bjtu.svnteen.nourriture.observer.IObserverBase;
import cn.edu.bjtu.svnteen.nourriture.observer.IProductJsonObserver;

/**
 * @warn 禁止修改
 * @author Tans
 * 观察者模式的回调MessageID
 */
public enum MessageID {
	OBSERVER_ID_RESERVE {
		public Class<? extends IObserverBase> getObserverClass() {
			return null;
		}
	},
	OBSERVER_APP {
		public Class<? extends IObserverBase> getObserverClass() {
			return IAppObserver.class;
		}
	},
	OBSERVER_PRODUCT_JSON {
		public Class<? extends IProductJsonObserver> getObserverClass() {
			return IProductJsonObserver.class;
		}
	};

	abstract Class<? extends IObserverBase> getObserverClass();
}