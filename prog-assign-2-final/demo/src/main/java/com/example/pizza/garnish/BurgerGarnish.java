package com.example.pizza.garnish;

import com.example.pizza.MenuItem;

public abstract class BurgerGarnish implements MenuItem {
    public abstract String toString(); 
	
	public String toNiceString() {
		return "Garnish: " + this.toString() + " $" + this.getPrice();
	}

	@Override
	public Double getPrice() {
		return 1.50;
	}

}
