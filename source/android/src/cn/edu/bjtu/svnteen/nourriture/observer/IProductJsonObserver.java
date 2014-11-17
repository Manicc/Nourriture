package cn.edu.bjtu.svnteen.nourriture.observer;

public interface IProductJsonObserver extends IObserverBase {

	void IProductJsonObserver_All(String json); // 获取全部Product数据

	void IProductJsonObserver_ID(String json); // 获取指定ID的Product数据

	void IProductJsonObserver_TagAndIngredient(String json); // 根据Tag和Ingredient获取的Product数据

	void IProductJsonObserver_Failed(); // 获取JSON数据失败

}
