package ph.ubx.xeatsv4.sellerFoodPanelFragment;

public class FoodDetails {

    public String Dishes,Quantity,Prices,Description,ImageURL,RandomUID,SellerId;

    public FoodDetails(String dishes, String quantity, String prices, String description, String imageURL, String randomUID, String sellerId) {
        Dishes = dishes;
        Quantity = quantity;
        Prices = prices;
        Description = description;
        ImageURL = imageURL;
        RandomUID = randomUID;
        SellerId = sellerId;
    }
}
