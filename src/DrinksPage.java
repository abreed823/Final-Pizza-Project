import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

/**
 * The functionality and display for the drink ordering page
 *
 * @author Team 2
 */
public class DrinksPage {
    private JPanel drinksPanel;
    private JButton logOutButton;
    private JRadioButton pepsiRadioButton;
    private JRadioButton orangeRadioButton;
    private JRadioButton rootBeerRadioButton;
    private JRadioButton dietRootBeerRadioButton;
    private JRadioButton dietOrangeRadioButton;
    private JRadioButton dietPepsiRadioButton;
    private JRadioButton sierraMistRadioButton;
    private JRadioButton lemonadeRadioButton;
    private JButton cancelButton;
    private JButton addToCartButton;
    private JRadioButton smallRadioButton;
    private JRadioButton mediumRadioButton;
    private JRadioButton largeRadioButton;
    private JLabel itemTotalLabel;
    private JLabel cartSubtotalLabel;
    private JLabel errorMessageLabel;
    private JComboBox<String> quantityComboBox;

    private ButtonGroup drinksButtonGroup;
    private ButtonGroup sizesButtonGroup;

    private boolean isSizeSelected;
    private boolean isDrinkSelected;

    private StringBuilder itemDescription;
    private String selectedSize;
    private String selectedDrink;
    private String[] drinkRow;

    /**
     * Constructor
     */
    public DrinksPage() {

        drinksButtonGroup = new ButtonGroup();
        drinksButtonGroup.add(pepsiRadioButton);
        drinksButtonGroup.add(dietPepsiRadioButton);
        drinksButtonGroup.add(orangeRadioButton);
        drinksButtonGroup.add(dietOrangeRadioButton);
        drinksButtonGroup.add(rootBeerRadioButton);
        drinksButtonGroup.add(dietRootBeerRadioButton);
        drinksButtonGroup.add(sierraMistRadioButton);
        drinksButtonGroup.add(lemonadeRadioButton);

        sizesButtonGroup = new ButtonGroup();
        sizesButtonGroup.add(smallRadioButton);
        sizesButtonGroup.add(mediumRadioButton);
        sizesButtonGroup.add(largeRadioButton);

        itemDescription = new StringBuilder();
        drinkRow = new String[4];
        selectedSize = "";
        selectedDrink = "";

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
                if(!(isSizeSelected && isDrinkSelected)){
                    errorMessageLabel.setText("*Please make all required selections.");
                }else if(quantityComboBox.getSelectedIndex() == 0){
                    errorMessageLabel.setText("*Please select a quantity");
                }else {
                    Main.updateCartTotal(Double.parseDouble(getQuantity()));
                    Main.updateItemAddedLabel(true);
                    buildDescriptionString();
                    addItemsToTables(String.valueOf(itemDescription));
                    resetPage();
                    Main.showCardLayout("startOrder");
                }
            }
        });
        ActionListener sizeButtonListener = new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(smallRadioButton.isSelected()){
                    selectedSize = "Small ";
                }else if(mediumRadioButton.isSelected()){
                    selectedSize = "Medium ";
                }else if(largeRadioButton.isSelected()){
                    selectedSize = "Large ";
                }
                
                isSizeSelected = true;
                updateItemTotalLabel(Integer.parseInt(getQuantity()));
            }
        };
        smallRadioButton.addActionListener(sizeButtonListener);
        mediumRadioButton.addActionListener(sizeButtonListener);
        largeRadioButton.addActionListener(sizeButtonListener);
        ActionListener drinkButtonListener = new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                if(pepsiRadioButton.isSelected()){
                    selectedDrink = "Pepsi";
                }else if(orangeRadioButton.isSelected()){
                    selectedDrink = "Orange";
                }else if(rootBeerRadioButton.isSelected()){
                    selectedDrink = "Root Beer";
                }else if(dietRootBeerRadioButton.isSelected()){
                    selectedDrink = "Diet Root Beer";
                }else if(dietOrangeRadioButton.isSelected()){
                    selectedDrink = "Diet Orange";
                }else if(dietPepsiRadioButton.isSelected()){
                    selectedDrink = "Diet Pepsi";
                }else if(sierraMistRadioButton.isSelected()){
                    selectedDrink = "Sierra Mist";
                }else if(lemonadeRadioButton.isSelected()){
                    selectedDrink = "Lemonade";
                }
                
                isDrinkSelected = true;
                updateItemTotalLabel(Integer.parseInt(getQuantity()));
            }
        };
        pepsiRadioButton.addActionListener(drinkButtonListener);
        orangeRadioButton.addActionListener(drinkButtonListener);
        rootBeerRadioButton.addActionListener(drinkButtonListener);
        dietRootBeerRadioButton.addActionListener(drinkButtonListener);
        dietOrangeRadioButton.addActionListener(drinkButtonListener);
        dietPepsiRadioButton.addActionListener(drinkButtonListener);
        sierraMistRadioButton.addActionListener(drinkButtonListener);
        lemonadeRadioButton.addActionListener(drinkButtonListener);
        drinksPanel.addComponentListener(new ComponentAdapter() {
            /**
             * Invoked when the component has been made visible.
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
                updateItemTotalLabel(Integer.parseInt(getQuantity()));
            }
        });
    }

    /**
     * Updates the Item Total label
     */
    public void updateItemTotalLabel(int quantity){
        if(isSizeSelected && isDrinkSelected){
            itemTotalLabel.setText("Item Total: $" + quantity + ".00");
        }
    }

    /**
     * Returns the drink quantity that the user selects
     * @return A string for the quantity of the drink
     */
    public String getQuantity(){
        if(quantityComboBox.getSelectedIndex() == 0 || quantityComboBox.getSelectedIndex() == 1){
            return "1";
        }
        return String.valueOf(quantityComboBox.getSelectedItem());
    }

    /**
     * Updates the cart subtotal label
     */
    public void updateCartSubtotalLabel(){
        cartSubtotalLabel.setText(Main.getCartTotalString());
    }

    /**
     * Creates the description of the pizza that will be added to the view cart and receipt tables
     */
    public void buildDescriptionString(){
        itemDescription.append(selectedSize);
        itemDescription.append(selectedDrink);
    }
    

    /**
     * Adds the new item to the tables of the view cart page anf the receipt page
     * @param itemDescription the description of the item that is being added
     */
    public void addItemsToTables(String itemDescription){
        drinkRow[0] = "Drink";
        drinkRow[1] = itemDescription;
        drinkRow[2] = String.valueOf(quantityComboBox.getSelectedItem());
        drinkRow[3] = "$" + quantityComboBox.getSelectedItem() + ".00";

        Main.addTableRow(drinkRow);
    }

    /**
     * Resets the page to its original state
     */
    public void resetPage(){
        drinksButtonGroup.clearSelection();
        sizesButtonGroup.clearSelection();
        quantityComboBox.setSelectedIndex(0);
        itemTotalLabel.setText("Item Total: $0.00");
        isSizeSelected = false;
        isDrinkSelected = false;
        errorMessageLabel.setText("*Required");
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
        return drinksPanel;
    }
}
