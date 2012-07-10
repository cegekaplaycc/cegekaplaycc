package models.stock;

public enum Food {

	POWER_BISCUITS("Power biscuits", 12),
	CARROTS("Carrots", 6),
	HAY("Hay", 0);

	public int price;
	public String label;

	private Food(String label, int price) {
		this.label = label;
		this.price = price;
	}

}
