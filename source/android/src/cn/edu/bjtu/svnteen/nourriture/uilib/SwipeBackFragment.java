
package cn.edu.bjtu.svnteen.nourriture.uilib;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

public class SwipeBackFragment extends Fragment implements SwipeBackFragmentBase {
    private SwipeBackFragmentHelper mHelper;
    
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mHelper = new SwipeBackFragmentHelper(this);
        mHelper.onFragmentCreateView();
    }

    @Override
	public SwipeBackLayout getSwipeBackLayout() {
    	if(mHelper==null)return null;
        return mHelper.getSwipeBackLayout();
    }
    
    @Override
    public void setSwipeBackEnable(boolean enable) {
    	if(getSwipeBackLayout()!=null)
    		getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
    	if(getSwipeBackLayout()!=null)
    		getSwipeBackLayout().scrollToFinishActivity();
    }
    
    @Override
    public void close(){
		FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
		fragmentManager.popBackStack();
    }
}
