package cn.edu.bjtu.svnteen.nourriture.bean;

public class Recipe {

	private int ID;
	private String name;
	private String processing;
	private String imageUrl;
	private int browseCount;
	private int collectCount;
	private String foodType;
	private long makingTime;
	private String makingTip;
	private int gastronomistID;
	private int[] ingredientsID;
	private int[] nutritionsID;
	private int[] tagsID;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessing() {
		return processing;
	}

	public void setProcessing(String processing) {
		this.processing = processing;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getBrowseCount() {
		return browseCount;
	}

	public void setBrowseCount(int browseCount) {
		this.browseCount = browseCount;
	}

	public int getCollectCount() {
		return collectCount;
	}

	public void setCollectCount(int collectCount) {
		this.collectCount = collectCount;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public long getMakingTime() {
		return makingTime;
	}

	public void setMakingTime(long makingTime) {
		this.makingTime = makingTime;
	}

	public String getMakingTip() {
		return makingTip;
	}

	public void setMakingTip(String makingTip) {
		this.makingTip = makingTip;
	}

	public int getGastronomistID() {
		return gastronomistID;
	}

	public void setGastronomistID(int gastronomistID) {
		this.gastronomistID = gastronomistID;
	}

	public int[] getIngredientsID() {
		return ingredientsID;
	}

	public void setIngredientsID(int[] ingredientsID) {
		this.ingredientsID = ingredientsID;
	}

	public int[] getNutritionsID() {
		return nutritionsID;
	}

	public void setNutritionsID(int[] nutritionsID) {
		this.nutritionsID = nutritionsID;
	}

	public int[] getTagsID() {
		return tagsID;
	}

	public void setTagsID(int[] tagsID) {
		this.tagsID = tagsID;
	}

}