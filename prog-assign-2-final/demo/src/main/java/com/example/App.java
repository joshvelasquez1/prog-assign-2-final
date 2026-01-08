package com.example;

import com.example.pizza.*;
import com.example.pizza.MenuItem;
import com.example.pizza.crust.*;
import com.example.pizza.sauce.*;
import com.example.pizza.topping.*;
import com.example.pizza.bun.*;
import com.example.pizza.patty.*;
import com.example.pizza.cheese.*;
import com.example.pizza.garnish.*;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App extends Application {
    private VBox selectionPanel;
    private List<Pizza> pizzas;
    private List<Burger> burgers;

    @Override
    public void start(Stage primaryStage) {
        pizzas = new ArrayList<>();
        burgers = new ArrayList<>();
        selectionPanel = new VBox();
        selectionPanel.setSpacing(10);

        ScrollPane scrollPane = new ScrollPane(selectionPanel);
        scrollPane.setFitToWidth(true);

        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(createPizzaForm(), createBurgerForm(), createGenerateReceiptButton(), scrollPane);

        Scene scene = new Scene(mainLayout, 500, 1000);
        primaryStage.setTitle("Build Your Pizza & Burger");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Create the form for building a pizza
    private VBox createPizzaForm() {
        VBox pizzaForm = new VBox(10);
        pizzaForm.setStyle("-fx-padding: 10; -fx-border-color: black;");

        ComboBox<PizzaCrust> crustChoice = new ComboBox<>();
        crustChoice.getItems().addAll(new ThinCrust(), new ThickCrust());

        ComboBox<PizzaSauce> sauceChoice = new ComboBox<>();
        sauceChoice.getItems().addAll(new TomatoSauce(), new AlfredoSauce());

        Label toppingsLabel = new Label("Select up to 4 Toppings:");
        VBox toppingsBox = createCheckboxGroup(new PepperoniTopping(), new SausageTopping(), new MushroomTopping(), new MozzarellaTopping(), new AsiagoTopping());

        Button addPizzaButton = new Button("Add Pizza");
        addPizzaButton.setOnAction(e -> {
            PizzaCrust crust = crustChoice.getValue();
            PizzaSauce sauce = sauceChoice.getValue();
            List<PizzaTopping> selectedToppings = getSelectedToppings(toppingsBox);

            if (crust != null && sauce != null) {
                Pizza pizza = new Pizza();
                pizza.setCrust(crust);
                pizza.setSauce(sauce);
                pizza.setTopping(new ArrayList<>(selectedToppings));
                pizzas.add(pizza);
                addToDisplay(pizza.display(), pizza.getPrice());
            }
        });

        pizzaForm.getChildren().addAll(
                new Label("Select Crust:"), crustChoice,
                new Label("Select Sauce:"), sauceChoice,
                toppingsLabel, toppingsBox,
                addPizzaButton
        );
        return pizzaForm;
    }

    // Create the form for building a burger
    private VBox createBurgerForm() {
        VBox burgerForm = new VBox(10);
        burgerForm.setStyle("-fx-padding: 10; -fx-border-color: black;");

        ComboBox<BurgerBun> bunChoice = new ComboBox<>();
        bunChoice.getItems().addAll(new SesameBun(), new BriocheBun(), new PotatoBun());

        Label pattyLabel = new Label("Select up to 4 Patties:");
        VBox pattyBox = createCheckboxGroup(new BeefPatty(), new ChickenPatty(), new VeggiePatty(), new ImpossiblePatty());

        Label cheeseLabel = new Label("Select up to 4 Cheese Slices:");
        VBox cheeseBox = createCheckboxGroup(new AmericanCheese(), new SwissCheese(), new CheddarCheese(), new PepperjackCheese());

        Label garnishLabel = new Label("Select up to 4 Garnishes:");
        VBox garnishBox = createCheckboxGroup(new LettuceGarnish(), new TomatoGarnish(), new PicklesGarnish(), new OnionRingsGarnish(), new BaconGarnish(), new GuacamoleGarnish());

        Button addBurgerButton = new Button("Add Burger");
        addBurgerButton.setOnAction(e -> {
            BurgerBun bun = bunChoice.getValue();
            List<BurgerPatty> selectedPatty = getSelectedPatty(pattyBox);
            List<BurgerCheese> selectedCheese = getSelectedCheese(cheeseBox);
            List<BurgerGarnish> selectedGarnish = getSelectedGarnish(garnishBox);

            if (bun != null) {
                Burger burger = new Burger();
                burger.setBun(bun);
                burger.setPatty(new ArrayList<>(selectedPatty));
                burger.setCheese(new ArrayList<>(selectedCheese));
                burger.setGarnish(new ArrayList<>(selectedGarnish));
                burgers.add(burger);
                addToDisplay(burger.display(), burger.getPrice());
            }
        });

        burgerForm.getChildren().addAll(
                new Label("Select Bun:"), bunChoice,
                pattyLabel, pattyBox,
                cheeseLabel, cheeseBox,
                garnishLabel, garnishBox,
                addBurgerButton
        );
        return burgerForm;
    }

    // Create the "Generate Receipt" button
    private Button createGenerateReceiptButton() {
        Button generateReceiptButton = new Button("Generate Receipt");
        generateReceiptButton.setOnAction(e -> generateReceipt());
        return generateReceiptButton;
    }

    // Helper method to create a group of checkboxes
    private <T extends MenuItem> VBox createCheckboxGroup(T... options) {
        VBox vbox = new VBox(5);
        for (T option : options) {
            CheckBox checkBox = new CheckBox(option.toString());
            checkBox.setUserData(option);
            vbox.getChildren().add(checkBox);
        }
        return vbox;
    }

    // Helper methods to get selected items
    private List<PizzaTopping> getSelectedToppings(VBox vbox) {
        return vbox.getChildren().stream()
                .filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected())
                .map(node -> (PizzaTopping) ((CheckBox) node).getUserData())
                .limit(4)
                .collect(Collectors.toList());
    }

    private List<BurgerPatty> getSelectedPatty(VBox vbox) {
        return vbox.getChildren().stream()
                .filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected())
                .map(node -> (BurgerPatty) ((CheckBox) node).getUserData())
                .limit(4)
                .collect(Collectors.toList());
    }

    private List<BurgerCheese> getSelectedCheese(VBox vbox) {
        return vbox.getChildren().stream()
                .filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected())
                .map(node -> (BurgerCheese) ((CheckBox) node).getUserData())
                .limit(4)
                .collect(Collectors.toList());
    }

    private List<BurgerGarnish> getSelectedGarnish(VBox vbox) {
        return vbox.getChildren().stream()
                .filter(node -> node instanceof CheckBox && ((CheckBox) node).isSelected())
                .map(node -> (BurgerGarnish) ((CheckBox) node).getUserData())
                .limit(4)
                .collect(Collectors.toList());
    }

    // Display item in selection panel
    private void addToDisplay(String details, double price) {
        StringBuilder displayText = new StringBuilder();
        displayText.append(details).append(" - Price: $").append(String.format("%.2f", price));
        
        Label displayLabel = new Label(details + " - Price: $" + String.format("%.2f", price));
        selectionPanel.getChildren().add(displayLabel);    }

    // Generate receipt
    private void generateReceipt() {
        pizzas.sort(Comparator.comparing(Pizza::getPrice));
        burgers.sort(Comparator.comparing(Burger::getPrice));
        Stage receiptStage = new Stage();
        receiptStage.setTitle("Receipt");

        VBox receiptLayout = new VBox(10);
        receiptLayout.setStyle("-fx-padding: 10; -fx-border-color: black;");

        Label receiptHeader = new Label("Receipt");
        receiptHeader.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");
        receiptLayout.getChildren().add(receiptHeader);

        double totalPrice = 0;

        for (Pizza pizza : pizzas) {
            String pizzaDetails = pizza.display() + " - Price: $" + String.format("%.2f", pizza.getPrice());
            receiptLayout.getChildren().add(new Label(pizzaDetails));
            totalPrice += pizza.getPrice();
        }

        for (Burger burger : burgers) {
            String burgerDetails = burger.display() + " - Price: $" + String.format("%.2f", burger.getPrice());
            receiptLayout.getChildren().add(new Label(burgerDetails));
            totalPrice += burger.getPrice();
        }

        Label totalPriceLabel = new Label("Total: $" + String.format("%.2f", totalPrice));
        totalPriceLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        receiptLayout.getChildren().add(totalPriceLabel);

        Scene receiptScene = new Scene(receiptLayout, 300, 400);
        receiptStage.setScene(receiptScene);
        receiptStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}