package com.example;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.pizza.Burger;
import com.example.pizza.Pizza;
import com.example.pizza.bun.BriocheBun;
import com.example.pizza.bun.BurgerBun;
import com.example.pizza.bun.SesameBun;
import com.example.pizza.cheese.AmericanCheese;
import com.example.pizza.cheese.BurgerCheese;
import com.example.pizza.crust.PizzaCrust;
import com.example.pizza.crust.ThickCrust;
import com.example.pizza.crust.ThinCrust;
import com.example.pizza.garnish.BurgerGarnish;
import com.example.pizza.garnish.LettuceGarnish;
import com.example.pizza.patty.BeefPatty;
import com.example.pizza.patty.BurgerPatty;
import com.example.pizza.sauce.AlfredoSauce;
import com.example.pizza.sauce.PizzaSauce;
import com.example.pizza.sauce.TomatoSauce;
import com.example.pizza.topping.PepperoniTopping;
import com.example.pizza.topping.PizzaTopping;
import com.example.pizza.topping.SausageTopping;

public class AppTest {

    private Pizza pizza;
    private Burger burger;
    private PizzaCrust crust;
    private PizzaSauce sauce;
    private ArrayList<PizzaTopping> toppings;
    private BurgerBun bun;
    private ArrayList<BurgerPatty> patties;
    private ArrayList<BurgerCheese> cheeses;
    private ArrayList<BurgerGarnish> garnishes;
    private ArrayList<Pizza> pizzas;
    private ArrayList<Burger> burgers;

    @BeforeEach
    public void setup() {
        // Initialize pizza components
        crust = new ThinCrust();
        sauce = new TomatoSauce();
        toppings = new ArrayList<>();
        toppings.add(new PepperoniTopping());
        pizza = new Pizza();
        pizza.setCrust(crust);
        pizza.setSauce(sauce);
        pizza.setTopping(toppings);

        // Initialize burger components
        bun = new SesameBun();
        patties = new ArrayList<>();
        patties.add(new BeefPatty());
        cheeses = new ArrayList<>();
        cheeses.add(new AmericanCheese());
        garnishes = new ArrayList<>();
        garnishes.add(new LettuceGarnish());
        burger = new Burger();
        burger.setBun(bun);
        burger.setPatty(patties);
        burger.setCheese(cheeses);
        burger.setGarnish(garnishes);

        pizzas = new ArrayList<>();
        burgers = new ArrayList<>();
    }

    @Test
    public void testPizzaToppingPriceIncrease() {
        double initialPrice = pizza.getPrice();
        pizza.getTopping().add(new SausageTopping());  // Adding another topping
        double newPrice = pizza.getPrice();
        assertTrue(newPrice > initialPrice, "Price should increase when a topping is added");
    }

    @Test
    public void testPizzaTotalPrice() {
        double expectedPrice = crust.getPrice() + sauce.getPrice() + pizza.getTopping().stream().mapToDouble(PizzaTopping::getPrice).sum();
        double actualPrice = pizza.getPrice();
        assertEquals(expectedPrice, actualPrice, "The total price of the pizza should be the sum of its crust, sauce, and toppings");
    }

    @Test
    public void testOrderPriceAfterPizza() {
        double orderPriceBefore = getOrderPrice();  
        pizza = new Pizza();
        pizza.setCrust(new ThinCrust());
        pizza.setSauce(new TomatoSauce());
        pizza.setTopping(new ArrayList<>());  
        pizzas.add(pizza);  
        double orderPriceAfter = getOrderPrice();
        assertTrue(orderPriceAfter > orderPriceBefore, "Order price should increase after adding a pizza");
    }

    @Test
    public void testOrderPriceAfterBurger() {
        double orderPriceBefore = getOrderPrice();  
        burger = new Burger();
        burger.setBun(new BriocheBun());
        burger.setPatty(new ArrayList<>());
        burger.setCheese(new ArrayList<>());
        burger.setGarnish(new ArrayList<>());
        burgers.add(burger);  // Add to order list
        double orderPriceAfter = getOrderPrice();
        assertTrue(orderPriceAfter > orderPriceBefore, "Order price should increase after adding a burger");
    }

    @Test
    public void testOrderPriceAfterTwoPizzas() {
        double orderPriceBefore = getOrderPrice();  
        Pizza pizza1 = new Pizza();
        pizza1.setCrust(new ThinCrust());
        pizza1.setSauce(new TomatoSauce());
        pizza1.setTopping(new ArrayList<>());
        pizzas.add(pizza1);

        Pizza pizza2 = new Pizza();
        pizza2.setCrust(new ThickCrust());
        pizza2.setSauce(new AlfredoSauce());
        pizza2.setTopping(new ArrayList<>());
        pizzas.add(pizza2);  

        double orderPriceAfter = getOrderPrice();
        assertTrue(orderPriceAfter > orderPriceBefore, "Order price should increase after adding two pizzas");
    }

    // Helper method to calculate total order price
    private double getOrderPrice() {
        double total = 0;
        for (Pizza pizza : pizzas) {
            total += pizza.getPrice();
        }
        for (Burger burger : burgers) {
            total += burger.getPrice();
        }
        return total;
    }

}
