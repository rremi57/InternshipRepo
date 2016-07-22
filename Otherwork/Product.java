
import java.util.*;

public class Product {
    
    private String name;
    private double price;
    private int currency;
    private double weight;
    
    /**
     * Default constructor
     */
    public Product() {
    }
    
    public Product(String productName, double productPrice, int moneyType, double productWeight ) {
        name = productName;
        price = productPrice;
        currency = moneyType;
        weight = productWeight;
    }
    
    public String getName() {
        return name;
    }

    public double getPrice() {
       return price;
    }

    public String getCurrencyType() {
       return currency;
    }

    public double getWeight() {
      return weight;
    }

    public void setName(String theName) {
       this.name = theName;
    }
    
    public void setPrice(double thePrice) {
        this.price = thePrice;
    }
    
    public void setCurrency(int currencyType) {
       this.currency = currencyType;
    }

    public void setWeight(double theWeight) {
        this.weight = theWeight;
    }

}
