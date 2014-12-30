package cn.edu.bjtu.svnteen.nourriture.observer;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.bean.Ingredient;

public interface IIngredientJsonObserver extends IObserverBase {
	void IIngredientJsonObserver_ALL(ArrayList<Ingredient> list);
}
