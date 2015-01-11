package cn.edu.bjtu.svnteen.nourriture.observer;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.bean.Comment;

public interface ICommentObserver extends IObserverBase {

	void ICommentObserver_success(ArrayList<Comment> list);

	void ICommentObserver_failed();

	void ICommentObserver_sendcommend_success();

	void ICommentObserver_sendcommend_failed();
}
