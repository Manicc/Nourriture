package cn.edu.bjtu.svnteen.nourriture.observer;

public interface IProductJsonObserver extends IObserverBase {

	void IProductJsonObserver_All(String json); // ��ȡȫ��Product����

	void IProductJsonObserver_ID(String json); // ��ȡָ��ID��Product����

	void IProductJsonObserver_TagAndIngredient(String json); // ����Tag��Ingredient��ȡ��Product����

	void IProductJsonObserver_Failed(); // ��ȡJSON����ʧ��

}
