package br.senai.sp.informatica.oop.bank;
import java.text.NumberFormat;
public class Product {
	private static NumberFormat formatter = NumberFormat.getCurrencyInstance();
	private long barCode;
	private String description;
	private double price;
	private String category;
	private boolean perishable;
	public Product() {
		// Standard constructor
	}
	public Product(long barCode, String description, double price, String category, boolean perishable) {
		super();
		setBarCode(barCode);
		setDescription(description);
		setPrice(price);
		setCategory(category);
		setPerishable(perishable);
	}
	public long getBarCode() {
		return barCode;
	}
	public void setBarCode(long barCode) {
		if(barCode < Math.abs(1_000_000_000))
			throw new IllegalArgumentException("You can't define a bar code with less than 10 digits.");
		this.barCode = barCode;
	}
	public String getDescription() {
		return description == null ? "<Empty>" : description;
	}
	public void setDescription(String description) {
		if(description.length() < 5)
			throw new IllegalArgumentException("You can't define a description with less than 5 characters.");
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		if(price <= 0)
			throw new IllegalArgumentException("The value for price must be a positive value.");
		this.price = price;
	}
	public String getCategory() {
		return category == null ? "<Empty>" : category;
	}
	public void setCategory(String category) {
		if(category.length() < 5)
			throw new IllegalArgumentException("You can't define a category with less than 10 characters.");
		this.category = category;
	}
	public boolean isPerishable() {
		return perishable;
	}
	public void setPerishable(boolean perishable) {
		this.perishable = perishable;
	}
	// Overwritten methods
	@Override
	public String toString() {
		return String.format("\tProduct description:\nBarcode: %-15d\nDescription: %s\nPrice: %s\nCategory: %s\nPerishable: %s", getBarCode(), getDescription(), formatter.format(getPrice()), getCategory(), isPerishable() ? "Yes" : "No");
	}
}
