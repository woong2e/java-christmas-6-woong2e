package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Receipt {
    int date;
    Map<Menu, Integer> orders;
    int totalOrderPrice;
    Menu giftMenu;
    Map<Event, Integer> benefitList;
    int benefitSum;
    int totalOrderDiscountPrice;
    List<String> decemberEventBadge = List.of("별", "트리", "산타");

    public Receipt(int date, Map<Menu, Integer> orders) {
        this.date = date;
        this.orders = orders;
    }

    public int getTotalOrderPrice() {
        totalOrderPrice = orders.values().stream().mapToInt(Integer::intValue).sum();
        return totalOrderPrice;
    }

    public Menu getGiftMenu() {
        if (totalOrderPrice >= 120_000) {
            giftMenu = Menu.CHAMPAGNE;
            return giftMenu;
        }
        giftMenu = Menu.NON;
        return giftMenu;
    }

    public Map<Event, Integer> getBenefitList() {

    }

}
