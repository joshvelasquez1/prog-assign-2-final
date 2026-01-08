package com.example.pizza;

import java.util.ArrayList;

import com.example.pizza.bun.BurgerBun;
import com.example.pizza.cheese.BurgerCheese;
import com.example.pizza.garnish.BurgerGarnish;
import com.example.pizza.patty.BurgerPatty;

public class Burger implements MenuItem {
    private BurgerBun bun;
    private ArrayList<BurgerPatty> pattiesList;
    private ArrayList<BurgerCheese> cheeseList;
    private ArrayList<BurgerGarnish> garnishesList;
    private ArrayList<MenuItem> selectionList;

    public Burger() {
        this.bun = null;
        this.pattiesList = new ArrayList<>();
        this.cheeseList = new ArrayList<>();
        this.garnishesList = new ArrayList<>();
        this.selectionList = new ArrayList<>();
    }

    public BurgerBun getBun(){
        return this.bun;
    }

    public void setBun(BurgerBun bun) {
        this.bun = bun;
        this.selectionList.add(bun);
    }

    public void setPatty(ArrayList<BurgerPatty> patties) {
        this.pattiesList = patties;
    }

    public void addPatty(BurgerPatty patty) {
        if (pattiesList.size() < 4) {
            this.pattiesList.add(patty);
            this.selectionList.add(patty);
        }
    }

    public void setCheese(ArrayList<BurgerCheese> cheeses) {
        this.cheeseList = cheeses;
    }

    public void addCheese(BurgerCheese cheese) {
        if (cheeseList.size() < 4) {
            this.cheeseList.add(cheese);
            this.selectionList.add(cheese);
        }
    }

    @Override
	public String toNiceString() {
		return "Pizza description here";
	}

    public void setGarnish(ArrayList<BurgerGarnish> garnish) {
        this.garnishesList = garnish;
    }

    public void addGarnish(BurgerGarnish garnish) {
        if (garnishesList.size() < 4) {
            this.garnishesList.add(garnish);
            this.selectionList.add(garnish);
        }
    }

    @Override
    public Double getPrice() {
        Double totalPrice = 0.0;
        totalPrice += this.bun.getPrice();

        for (BurgerPatty patty : this.pattiesList) {
            totalPrice += patty.getPrice();
        }
        for (BurgerCheese cheese : this.cheeseList) {
            totalPrice += cheese.getPrice();
        }
        for (BurgerGarnish garnish : this.garnishesList) {
            totalPrice += garnish.getPrice();
        }
        return totalPrice;
    }

    public String display() {
        StringBuilder displayText = new StringBuilder();
        displayText.append("Burger: $").append(this.getPrice()).append("\n");
        displayText.append("    ").append(this.bun.toNiceString()).append("\n");
        for (BurgerPatty t : this.pattiesList) {
            displayText.append("    ").append(t.toNiceString()).append("\n");
        }
        for (BurgerCheese t : this.cheeseList) {
            displayText.append("    ").append(t.toNiceString()).append("\n");
        }
        for (BurgerGarnish t : this.garnishesList) {
            displayText.append("    ").append(t.toNiceString()).append("\n");
        }
        return displayText.toString();
    }

   // public void displayItems() {
   //     System.out.println("Burger: $" + getPrice());
   //     for (MenuItem item : selectionList) {
   //         System.out.println("    " + item.toNiceString());
   //     }
   // }

    public void sortItems() {
        selectionList.sort(null);
    }
}
