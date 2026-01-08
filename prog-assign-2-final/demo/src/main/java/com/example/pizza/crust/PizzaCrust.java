package com.example.pizza.crust;

import com.example.pizza.MenuItem;

public abstract class PizzaCrust implements MenuItem {
	protected String ingredient;
	
	public PizzaCrust() {
		this.ingredient = "";
	}
	
	public abstract String toString(); 

	public String toNiceString() {
		return "Crust: " + this.toString() + " (" + this.ingredient + ") $" + this.getPrice();
	}
	
	public String getIngredient() {
		return this.ingredient;
	}
	
	public void setIngredient(String ingredient) {
		this.ingredient = ingredient;
	}

	public String checkIntegrity() {
		return "";
	}
	
	@Override
	public Double getPrice() {
		return 3.0;
	}

}
