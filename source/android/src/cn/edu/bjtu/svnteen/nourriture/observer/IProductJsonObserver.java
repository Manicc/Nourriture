package cn.edu.bjtu.svnteen.nourriture.observer;

public interface IProductJsonObserver extends IObserverBase {

	void IProductJsonObserver_All(String json); // 获取全部Product的回调方法

	void IProductJsonObserver_ID(String json); // 根据指定ID获取product的回调方法

	void IProductJsonObserver_TagAndIngredient(String json); // 根据Tag和Ingredient获取的product的回调方法

	void IProductJsonObserver_Failed(); // 获取json失败的回调方法

}
