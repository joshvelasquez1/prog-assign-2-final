package com.example.pizza.sauce;

import com.example.pizza.MenuItem;

public abstract class PizzaSauce implements MenuItem {
	public abstract String toString(); 
	
	public String toNiceString() {
		return "Sauce: " + this.toString() + " $" + this.getPrice();
	}
	
	@Override
	public Double getPrice() {
		return 0.25;
	}
	
}
