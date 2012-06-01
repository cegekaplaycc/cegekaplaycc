package models.stock;

public enum Food {
	POWER_BISCUITS("Power biscuits", 12),
	CARROTS("Carrots", 6),
	HAY("Hay", 0);

	public long price;
	public String name;

	private Food(String name, long price) {
		this.name = name;
		this.price = price;
	}

}
