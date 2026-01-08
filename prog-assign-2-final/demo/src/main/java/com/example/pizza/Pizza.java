package com.example.pizza;
import java.util.ArrayList;
import java.util.Collections;

import com.example.pizza.crust.PizzaCrust;
import com.example.pizza.sauce.PizzaSauce;
import com.example.pizza.topping.PizzaTopping;

/**
 * Pizza represents a pizza in our application. It holds together all the usual components of pizza like
 * crust, sauce and toppings. For our application, we limit to one crust, one sauce, and multiple toppings.
 */
public class Pizza implements MenuItem {
	private PizzaCrust crust;
	private PizzaSauce sauce;
	private ArrayList<PizzaTopping> toppingList;
	private ArrayList<MenuItem> selectionList;
	
	public Pizza() {
		this.crust = null;
		this.sauce = null;
		this.toppingList = new ArrayList<PizzaTopping>();
		this.selectionList = new ArrayList<MenuItem>();
	}

	public PizzaCrust getCrust() {
		return this.crust;
	}

	public void setCrust(PizzaCrust crust) {
		this.crust = crust;
		this.selectionList.add(crust);
	}

	public PizzaSauce getSauce() {
		return this.sauce;
	}

	public void setSauce(PizzaSauce sauce) {
		this.sauce = sauce;
		this.selectionList.add(sauce);
	}

	public ArrayList<PizzaTopping> getTopping() {
		return this.toppingList;
	}

	public void setTopping(ArrayList<PizzaTopping> toppings) {
		this.toppingList = toppings;
	}

	@Override
	public String toNiceString() {
		return "Pizza description here";
	}

	/**
	 * addTopping appends a new topping to the list of toppings.
	 * @param topping is any valid PizzaTopping
	 */
	public void addTopping(PizzaTopping topping) {
		this.toppingList.add(topping);
		this.selectionList.add(topping);
	}
	
	public String display() {
		StringBuilder displayText = new StringBuilder();
		displayText.append("Pizza: $").append(this.getPrice()).append("\n");
		displayText.append("    ").append(this.crust.toNiceString()).append("\n");
		displayText.append("    ").append(this.sauce.toNiceString()).append("\n");
		
		for (PizzaTopping t : this.toppingList) {
			displayText.append("    ").append(t.toNiceString()).append("\n");
		}
		return displayText.toString();
	}

	//public void displayItems() {
	//	System.out.println("Pizza: $" + this.getPrice());
	//	for (MenuItem m : this.selectionList) {
	//		System.out.println("    " + m.toNiceString());
	//	}
	//}

	// @Override
	// public Double getPrice() {
		// Double totalPrice = 0.0;
		// totalPrice += this.crust.getPrice();
		// totalPrice += this.sauce.getPrice();
		// for (PizzaTopping t : this.toppingList) {
			// totalPrice += t.getPrice();
		// }
		// return totalPrice;
	// }

	@Override
	public Double getPrice() {
		Double totalPrice = 0.0;
		totalPrice += this.crust.getPrice();
    	totalPrice += this.sauce.getPrice();
    	for (PizzaTopping topping : this.toppingList) {
        	totalPrice += topping.getPrice();
    	}
		return totalPrice;
	}

	public void sortItems() {
		Collections.sort(this.selectionList);
	}
}
