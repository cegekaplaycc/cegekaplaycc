package models;

import models.stock.Food;

import static java.lang.Integer.parseInt;

public class Purchase {

    public Food foodId;
    public String amount;

    public int getPrice() {
        return parseInt(amount) * foodId.price;
    }


}
