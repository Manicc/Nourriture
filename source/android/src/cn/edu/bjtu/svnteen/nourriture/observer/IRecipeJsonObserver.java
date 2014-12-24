package cn.edu.bjtu.svnteen.nourriture.observer;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public interface IRecipeJsonObserver extends IObserverBase{
	
	void IRecipeJsonObserver_All(ArrayList<Recipe> list);

}
