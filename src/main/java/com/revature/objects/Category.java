package com.revature.objects;

public class Category {
private String category;
private String color;
private int amount;
private double percentage;

public Category() {
	super();
}
public Category(String category, String color, int amount) {
	super();
	this.category = category;
	this.color = color;
	this.amount = amount;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getColor() {
	return color;
}
public void setColor(String color) {
	this.color = color;
}
public int getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}


public double getPercentage() {
	return percentage;
}
public void setPercentage(double percentage) {
	this.percentage = percentage;
}
@Override
public String toString() {
	return "Category [category=" + category + ", color=" + color + ", amount=" + amount + ", percentage=" + percentage
			+ "]";
}



}
