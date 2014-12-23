package cn.edu.bjtu.svnteen.nourriture.observer;

import java.util.ArrayList;

import cn.edu.bjtu.svnteen.nourriture.bean.Product;

public interface IProductJsonObserver extends IObserverBase {

	void IProductJsonObserver_All(ArrayList<Product> products); // 获取全部Product的回调方法

	void IProductJsonObserver_ID(Product product); // 根据指定ID获取product的回调方法
	
	void IProductJsonObserver_Detail(Product product);// 获取详细product的方法
	
	void IProductJsonObserver_Detail_ingredients(Product product);// 获取ingredient的详细数据
	
	void IProductJsonObserver_Detail_nutrition(Product product);// 获取nutrition的详细数据

	void IProductJsonObserver_TagAndIngredient(String json); // 根据Tag和Ingredient获取的product的回调方法

	void IProductJsonObserver_Failed(); // 获取json失败的回调方法

}
