package cn.edu.bjtu.svnteen.nourriture.utils;

public class UrlManagerUtils {

	private static final String BASEURL = "http://nourriture.sinaapp.com/";
	private static final String PRODUCT = "product/";
	private static final String INGREDIENT = "ingredient/";
	private static final String NUTRITION = "nutrition/";

	// 参数为0，则获取全部数据
	public static String getProductUrl(int id) {
		StringBuilder builder = new StringBuilder(BASEURL);
		builder.append(PRODUCT);
		if (id != 0) {
			builder.append(id);
		}
		return builder.toString();
	}

	public static String getIngredientUrl(int id) {
		StringBuilder builder = new StringBuilder(BASEURL);
		builder.append(INGREDIENT);
		if (id != 0) {
			builder.append(id);
		}
		return builder.toString();
	}

	public static String getNutritionUrl(int id) {
		StringBuilder builder = new StringBuilder(BASEURL);
		builder.append(NUTRITION);
		if (id != 0) {
			builder.append(id);
		}
		return builder.toString();
	}

}
