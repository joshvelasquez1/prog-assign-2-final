package com.example.pizza.bun;

import com.example.pizza.MenuItem;

public abstract class BurgerBun implements MenuItem {
	public abstract String toString(); 
	
	public String toNiceString() {
		return "Topping: " + this.toString() + " $" + this.getPrice();
	}

	@Override
	public Double getPrice() {
		return 1.50;
	}

}
