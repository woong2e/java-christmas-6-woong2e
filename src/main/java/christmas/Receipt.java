package christmas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {
    private int date;
    private Map<Menu, Integer> orders;
    private int totalOrderPrice;
    private Menu giftMenu;
    private Map<Event, Integer> benefitList = new HashMap<>();
    private int benefitSum = 0;
    private int totalOrderDiscountPrice;
    private final List<String> decemberEventBadge = List.of("없음","별", "트리", "산타");

    public Receipt(int date, Map<Menu, Integer> orders) {
        this.date = date;
        this.orders = orders;
    }

    private int findOrderNumber(String meal) {
        int number = 0;
        for(Map.Entry<Menu, Integer> order : orders.entrySet()) {
            if (order.getKey().getMeal().equals(meal)) {
                number += order.getValue();
            }
        }
        return number;
    }

    public int getDate() {
        return date;
    }
    public Map<Menu, Integer> getOrders() {
        return orders;
    }

    public int getTotalOrderPrice() {
        for(Map.Entry<Menu, Integer> order : orders.entrySet()) {
            totalOrderPrice += order.getKey().getPrice() * order.getValue();
        }
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
        if (Event.CHRISTMAS_D_DAY_DISCOUNT.getDate().contains(date)) {
            benefitList.put(Event.CHRISTMAS_D_DAY_DISCOUNT, 1);
        }
        if (Event.WEEKDAY_DISCOUNT.getDate().contains(date)) {
            benefitList.put(Event.WEEKDAY_DISCOUNT, findOrderNumber("dessert"));
        }
        if (Event.WEEKEND_DISCOUNT.getDate().contains(date)) {
            benefitList.put(Event.WEEKEND_DISCOUNT, findOrderNumber("main"));
        }
        if (Event.SPECIAL_DISCOUNT.getDate().contains(date)) {
            benefitList.put(Event.SPECIAL_DISCOUNT, 1);
        }
        if (giftMenu == Menu.CHAMPAGNE) {
            benefitList.put(Event.PRESENTATION_EVENT, 1);
        }
        return benefitList;
    }


    public int getBenefitSum() {
        for (Map.Entry<Event, Integer> benefit : benefitList.entrySet()) {
            if (benefit.getKey().getDiscountKind().equals("크리스마스 디데이 할인")) {
                benefitSum += benefit.getKey().getBenefit() - 100 * (date-1);
                continue;
            }
            benefitSum += benefit.getKey().getBenefit() * benefit.getValue();
        }
        return benefitSum;
    }

    public int getTotalOrderDiscountPrice() {
        totalOrderDiscountPrice = totalOrderPrice + benefitSum;
        for (Event event : benefitList.keySet()) {
            if (event.getDiscountKind().equals("증정 이벤트")) {
                totalOrderDiscountPrice -= event.getBenefit();
                break;
            }
        }
        return totalOrderDiscountPrice;
    }

    public String getDecemberEventBadge() {
        if (-benefitSum >= 20_000) {
            return decemberEventBadge.get(3);
        } else if (-benefitSum >= 10_000) {
            return decemberEventBadge.get(2);
        } else if (-benefitSum >= 5_000) {
            return decemberEventBadge.get(1);
        }
        return decemberEventBadge.get(0);
    }

}
