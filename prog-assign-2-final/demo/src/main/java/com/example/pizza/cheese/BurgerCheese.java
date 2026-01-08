package com.example.pizza.cheese;

import com.example.pizza.MenuItem;

public abstract class BurgerCheese implements MenuItem {
	public abstract String toString(); 
	
	public String toNiceString() {
		return "Cheese: " + this.toString() + " $" + this.getPrice();
	}

	@Override
	public Double getPrice() {
		return 1.50;
	}

}
