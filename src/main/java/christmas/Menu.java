package christmas;

import java.util.ArrayList;
import java.util.List;

public enum Menu {
    MUSHROOM_SOUP("appetizer","양송이수프", 6_000),
    TAPAS("appetizer", "타파스", 5_500),
    CAESAR_SALAD("appetizer", "시저샐러드" ,8_000),

    T_BONE_STEAK("main", "티본스테이크", 55_000),
    BARBECUE_RIBS("main", "바비큐립", 54_000),
    SEAFOOD_PASTA("main", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("main", "크리스마스파스타", 25_000),

    CHOCOLATE_CAKE("dessert", "초코케이크",  15_000),
    ICE_CREAM("dessert", "아이스크림", 5_000),

    DIET_COKE("drink", "제로콜라", 3_000),
    RED_WINE("drink", "레드와인",60_000),
    CHAMPAGNE("drink", "샴페인", 25_000),

    NON("없음", "없음", 0);


    private final String meal;
    private final String menuKoreanName;
    private final int price;

    Menu(String meal, String menuKoreanName, int price){
        this.meal = meal;
        this.menuKoreanName = menuKoreanName;
        this.price = price;
    }

    public String getMeal() {
        return meal;
    }

    public String getMenuKoreanName() {
        return menuKoreanName;
    }

    public int getPrice() {
        return price;
    }

    public static List<String> getDrinkMenuKoreanNameList(){
        List<String> drinkMenuKoreanName = new ArrayList<>();
        for (Menu menu : Menu.values()) {
            if (menu.meal.equals("drink")) {
                drinkMenuKoreanName.add(menu.menuKoreanName);
            }
        }
        return drinkMenuKoreanName;
    }


}
