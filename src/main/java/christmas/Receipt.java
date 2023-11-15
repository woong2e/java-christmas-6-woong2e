package christmas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Receipt {
    private final int date;
    private final Map<Menu, Integer> orders;
    private int totalOrderPrice;
    private Menu giftMenu;
    private Map<Event, Integer> benefitList = null;
    private int benefitSum = 0;
    private int totalOrderDiscountPrice;
    private final List<String> decemberEventBadge = List.of("없음","별", "트리", "산타");

    public Receipt(int date, Map<Menu, Integer> orders) {
        this.date = date;
        this.orders = orders;
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
        if (totalOrderPrice < 10_000) {
            return benefitList;
        }
        putBenefitList();
        return benefitList;
    }

    private void putBenefitList() {
        benefitList = new HashMap<>();
        int[] orderNum = new int[]{1,findOrderNumber("dessert"),findOrderNumber("main"), 1};
        int i = 0;
        for (Event event : Event.values()) {
            if (event == Event.PRESENTATION_EVENT) {
                continue;
            }
            if (event.getDate().contains(date)) {
                benefitList.put(event, orderNum[i]);
            }
            i++;
        }
        if (giftMenu == Menu.CHAMPAGNE) {
            benefitList.put(Event.PRESENTATION_EVENT, 1);
        }
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


    public int getBenefitSum() {
        if (benefitList == null) {
            benefitSum = 0;
            return benefitSum;
        }
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
        if (benefitList == null) {
            return totalOrderDiscountPrice;
        }
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
