import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

/**
 * The functionality and display for the Pizza Ordering page
 *
 * @author Team 2
 */
public class PizzaPage {
    private JButton logOutButton;
    private JPanel pizzaPanel;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JRadioButton xLargeRadioButton;
    private JCheckBox pepperoniCheckBox;
    private JCheckBox onionCheckBox;
    private JCheckBox mushroomCheckBox;
    private JCheckBox hamCheckBox;
    private JCheckBox sausageCheckBox;
    private JCheckBox pineappleCheckBox;
    private JCheckBox greenPepperCheckBox;
    private JCheckBox tomatoCheckBox;
    private JCheckBox extraCheeseCheckBox;
    private JRadioButton originalRadioButton;
    private JRadioButton panRadioButton;
    private JRadioButton thinRadioButton;
    private JButton cancelButton;
    private JButton addToCartButton;
    private JLabel itemTotalLabel;
    private JLabel cartSubtotalLabel;
    private JLabel errorMessageLabel;
    private JComboBox<String> quantityComboBox;

    private ButtonGroup sizesButtonGroup;
    private ButtonGroup crustButtonGroup;

    private double toppingsCounter;

    private ArrayList<JCheckBox> boxesToDisable;
    private boolean disabled;

    private double sizePrice;
    private double toppingsPrice;
    private double pricePerTopping;
    private String itemTotalPrice;

    private boolean IsSizeSelected;
    private boolean isCrustSelected;

    private StringBuilder itemDescription;
    private String selectedSize;
    private String selectedCrust;
    private ArrayList<String> selectedToppings;
    private String[] pizzaRow;

    /**
     * Constructor
     */
    public PizzaPage() {
        IsSizeSelected = false;
        isCrustSelected = false;

        boxesToDisable = new ArrayList<JCheckBox>();
        addCheckBoxes();
        disabled = false;
        toppingsCounter = 0;
        pricePerTopping = 0;

        sizesButtonGroup = new ButtonGroup();
        sizesButtonGroup.add(smallRadioButton);
        sizesButtonGroup.add(mediumRadioButton);
        sizesButtonGroup.add(largeRadioButton);
        sizesButtonGroup.add(xLargeRadioButton);

       crustButtonGroup = new ButtonGroup();
       crustButtonGroup.add(originalRadioButton);
       crustButtonGroup.add(thinRadioButton);
       crustButtonGroup.add(panRadioButton);

       itemDescription = new StringBuilder();
       selectedSize = "";
       selectedCrust = "";
       selectedToppings = new ArrayList<String>();
       pizzaRow = new String[4];

        logOutButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.resetProgram();
                Main.showCardLayout("welcome");
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                resetPage();
                Main.updateItemAddedLabel(false);
                Main.showCardLayout("startOrder");
            }
        });
        addToCartButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!(IsSizeSelected && isCrustSelected)){
                    errorMessageLabel.setText("*Please make all required selections.");
                }else if(quantityComboBox.getSelectedIndex() == 0){
                    errorMessageLabel.setText("*Please select a quantity");
                }else {
                    Main.updateCartTotal((Double.parseDouble(getQuantity()))*(sizePrice + toppingsPrice));
                    Main.updateItemAddedLabel(true);
                    buildDescriptionString();
                    addItemsToTables(String.valueOf(itemDescription));
                    resetPage();
                    Main.showCardLayout("startOrder");
                }
            }
        });

        pepperoniCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(pepperoniCheckBox, "Pepperoni");
            }
        });
        hamCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(hamCheckBox, "Ham");
            }
        });
        greenPepperCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(greenPepperCheckBox, "Green Peppers");
            }
        });
        onionCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(onionCheckBox, "Onion");
            }
        });
        sausageCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(sausageCheckBox, "Sausage");
            }
        });
        tomatoCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(tomatoCheckBox, "Tomato");
            }
        });
        mushroomCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(mushroomCheckBox, "Mushroom");
            }
        });
        pineappleCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(pineappleCheckBox, "Pineapple");
            }
        });
        extraCheeseCheckBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleCheckBox(extraCheeseCheckBox, "Extra Cheese");
            }
        });
        smallRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = "Small";
                pricePerTopping = 0.5;
                updateSizePrice(4.00);
            }
        });
        mediumRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = "Medium";
                pricePerTopping = 0.75;
                updateSizePrice(6.00);
            }
        });
        largeRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = "Large";
                pricePerTopping = 1;
                updateSizePrice(8.00);
            }
        });
        xLargeRadioButton.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedSize = "Extra Large";
                pricePerTopping = 1.25;
                updateSizePrice(10.00);
            }
        });
        ActionListener listener = new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                isCrustSelected = true;
                if(originalRadioButton.isSelected()){
                    selectedCrust = "Original Crust";
                }else if(panRadioButton.isSelected()){
                    selectedCrust = "Pan Crust";
                }else if(thinRadioButton.isSelected()){
                    selectedCrust = "Thin Crust";
                }
            }
        };
        originalRadioButton.addActionListener(listener);
        panRadioButton.addActionListener(listener);
        thinRadioButton.addActionListener(listener);
        pizzaPanel.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component has been made visible
             *
             * @param e
             */
            @Override
            public void componentShown(ComponentEvent e) {
                super.componentShown(e);
                updateCartSubtotalLabel();
            }
        });
        quantityComboBox.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                updateItemTotal(sizePrice, toppingsPrice);
            }
        });
    }

    /**
     * Adds all of the checkboxes to an ArrayList
     */
    public void addCheckBoxes(){
        boxesToDisable.clear();
        boxesToDisable.add(pepperoniCheckBox);
        boxesToDisable.add(onionCheckBox);
        boxesToDisable.add(mushroomCheckBox);
        boxesToDisable.add(hamCheckBox);
        boxesToDisable.add(sausageCheckBox);
        boxesToDisable.add(pineappleCheckBox);
        boxesToDisable.add(greenPepperCheckBox);
        boxesToDisable.add(tomatoCheckBox);
        boxesToDisable.add(extraCheeseCheckBox);
    }

    /**
     * Updates the boxesToDisable ArrayList whenever a checkbox is toggled
     * @param box the box that is toggled
     */
    public void toggleCheckBox(JCheckBox box, String toppingName){
        if(box.isSelected()){
            boxesToDisable.remove(box);
            selectedToppings.add(toppingName);
            changeToppingsCounter(true);
        }else{
            boxesToDisable.add(box);
            selectedToppings.remove(toppingName);
            changeToppingsCounter(false);
        }
    }

    /**
     * Updates the topping counter and price whenever a checkbox is toggled
     * @param boxIsChecked indicates whether a box has been checked or not
     */
    public void changeToppingsCounter(boolean boxIsChecked){
        if(boxIsChecked){
            toppingsCounter++;
        }else{
            toppingsCounter--;
        }
        updateToppingsPrice(pricePerTopping, toppingsCounter);
        if(toppingsCounter == 4){
            disableCheckBoxes();
        }else if(disabled){
            enableCheckBoxes();
        }
    }

    /**
     * Disables all checkboxes left is the ArrayList
     */
    public void disableCheckBoxes(){
        for(JCheckBox box: boxesToDisable){
            box.setEnabled(false);
        }
        disabled = true;
    }

    /**
     * Enables all checkboxes
     */
    public void enableCheckBoxes(){
        for(JCheckBox box: boxesToDisable){
            box.setEnabled(true);
        }
        disabled = false;
    }

    /**
     * Updates the price of the pizza based on the size
     * @param sizePrice the prize of the selected pizza size
     */
    public void updateSizePrice(double sizePrice){
        IsSizeSelected = true;
        this.sizePrice = sizePrice;
        updateToppingsPrice(pricePerTopping, toppingsCounter);
    }

    /**
     * Updates the price of the toppings depending on the pizza size
     * @param pricePerTopping the price per topping
     * @param toppingsCounter how many topping are selected
     */
    public void updateToppingsPrice(double pricePerTopping, double toppingsCounter){
        toppingsPrice = pricePerTopping * toppingsCounter;
        updateItemTotal(sizePrice, toppingsPrice);
    }

    /**
     * Updates the total price of the pizza
     * @param sizePrice the prize of the pizza size
     * @param toppingsPrice the price of the toppings
     */
    public void updateItemTotal(double sizePrice, double toppingsPrice){
        itemTotalPrice = String.format("%.2f",(Double.parseDouble(getQuantity()))*(sizePrice + toppingsPrice));
        itemTotalLabel.setText("Item Total: $" + itemTotalPrice);
    }

    /**
     * Updates the cart subtotal label
     */
    public void updateCartSubtotalLabel(){
        cartSubtotalLabel.setText(Main.getCartTotalString());
    }

    /**
     * Returns the pizza quantity that the user selects
     * @return A string for the quantity of the pizza
     */
    public String getQuantity(){
        if(quantityComboBox.getSelectedIndex() == 0 || quantityComboBox.getSelectedIndex() == 1){
            return "1";
        }
        return String.valueOf(quantityComboBox.getSelectedItem());
    }

    /**
     * Creates the description of the pizza that will be added to the view cart and receipt tables
     */
    public void buildDescriptionString(){
        itemDescription.append(selectedSize);
        itemDescription.append(", ");
        itemDescription.append(selectedCrust);

        if(toppingsCounter > 0){
            for(String topping: selectedToppings){
                itemDescription.append(", ");
                itemDescription.append(topping);
            }
        }
    }

    /**
     * Adds the new item to the tables of the view cart page anf the receipt page
     * @param itemDescription the description of the item that is being added
     */
    public void addItemsToTables(String itemDescription){
        pizzaRow[0] = "Pizza";
        pizzaRow[1] = itemDescription;
        pizzaRow[2] = String.valueOf(quantityComboBox.getSelectedItem());
        pizzaRow[3] = "$" + itemTotalPrice;

        Main.addTableRow(pizzaRow);
    }

    /**
     * Resets the page to its original state
     */
    public void resetPage(){
        addCheckBoxes();
        enableCheckBoxes();
        toppingsCounter = 0;
        for(JCheckBox box: boxesToDisable){
            box.setSelected(false);
        }

        sizesButtonGroup.clearSelection();
        crustButtonGroup.clearSelection();
        pricePerTopping = 0;
        sizePrice = 0;
        quantityComboBox.setSelectedIndex(0);
        itemTotalLabel.setText("Item Total: $0.00");
        errorMessageLabel.setText("*Required");
        IsSizeSelected = false;
        isCrustSelected = false;

        itemDescription.setLength(0);
    }

    /**
     * Completely resets page and cart when user logs out
     */
    public void factoryReset(){
        resetPage();
        updateCartSubtotalLabel();
    }

    /**
     * Returns the JPanel to the Main class
     * @return the panel to return
     */
    public JPanel getPanel(){
        return pizzaPanel;
    }
}
