package christmas;

public enum Menu {
    MUSHROOM_SOUP("appetizer", 6_000),
    TAPAS("appetizer", 5_500),
    CAESAR_SALAD("appetizer", 8_000),

    T_BONE_STEAK("main", 55_000),
    BARBECUE_RIBS("main", 54_000),
    SEAFOOD_PASTA("main", 35_000),
    CHRISTMAS_PASTA("main", 25_000),

    CHOCOLATE_CAKE("dessert", 15_000),
    ICE_CREAM("dessert", 5_000),

    DIET_COKE("drink", 3_000),
    RED_WINE("drink", 60_000),
    CHAMPAGNE("drink", 25_000);



    String meal;
    int price;

    Menu(String meal, int price){
        this.meal = meal;
        this.price = price;
    }
}
