package com.example.pizza.patty;

import com.example.pizza.MenuItem;

public abstract class BurgerPatty implements MenuItem {
	public abstract String toString(); 
	
	public String toNiceString() {
		return "Patty: " + this.toString() + " $" + this.getPrice();
	}

	@Override
	public Double getPrice() {
		return 3.00;
	}

}
