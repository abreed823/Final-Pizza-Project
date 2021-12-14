import java.util.ArrayList;
/**
 * Updates the total for the user's car
 */
public class CartTotal {
    private double cartTotal;
    private String totalPriceString;

    /**
     * Constructor
     */
    CartTotal(){
        cartTotal = 0;
        totalPriceString = "Cart Subtotal: $" + String.format("%.2f", cartTotal);
    }

    /**
     * Returns the cart total as a String
     * @return the cart total String
     */
    public String getCartTotalString(){
        return totalPriceString;
    }

    /**
     * Returns the cart total as a double
     * @return the cart total double
     */
    public double getCartTotalDouble(){
        return cartTotal;
    }

    /**
     * Updates the price of the car
     * @param price the price to add to the cart
     */
    public void updateCartTotal(double price){
        cartTotal += price;
        totalPriceString = "Cart Subtotal: $" + String.format("%.2f", cartTotal);
    }

    /**
     * Completely resets the cart when the user logs out
     */
    public void resetCartTotal(){
        cartTotal = 0;
        totalPriceString = "Cart Subtotal: $" + String.format("%.2f", cartTotal);
    }
}
