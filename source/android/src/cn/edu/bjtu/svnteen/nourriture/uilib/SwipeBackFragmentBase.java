package cn.edu.bjtu.svnteen.nourriture.uilib;

public interface SwipeBackFragmentBase {
	
    public abstract SwipeBackLayout getSwipeBackLayout();

    public abstract void setSwipeBackEnable(boolean enable);

    public abstract void scrollToFinishActivity();
    
    public abstract void close();

}
