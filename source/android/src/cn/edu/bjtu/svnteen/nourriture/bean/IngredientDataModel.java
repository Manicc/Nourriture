package cn.edu.bjtu.svnteen.nourriture.bean;

import java.util.ArrayList;

/**
 * 给从网上请求的无序的Ingredient 根据category排序
 * @author Tans
 *
 */
public class IngredientDataModel {
	
	private int id;
	private String name;
	private ArrayList<Ingredient> ingredientList;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Ingredient> getIngredientList() {
		return ingredientList;
	}
	public void setIngredientList(ArrayList<Ingredient> ingredientList) {
		this.ingredientList = ingredientList;
	}

}
