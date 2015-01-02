package cn.edu.bjtu.svnteen.nourriture.observer;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.bean.Recipe;

public interface IRecipeJsonObserver extends IObserverBase{
	
	void IRecipeJsonObserver_All(ArrayList<Recipe> list);// 获取所有食谱
	
	void IRecipeJsonObserver_Detail_ingredients(Recipe recipe);// 获取食谱中的ingredients

}
