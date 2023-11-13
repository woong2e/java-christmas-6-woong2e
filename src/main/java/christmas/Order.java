package christmas;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {

    public Map<Menu, Integer> check(String input) {
        try {
            Map<Menu, Integer> order = splitInput(input);
            return order;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    private Map<Menu, Integer> splitInput(String menu) {
        List<String> orderList = Arrays.asList(menu.split(","));

        List<String> menuList = new ArrayList<>();
        List<Integer> amountList = new ArrayList<>();
        validateNormalInput(orderList, menuList, amountList);


        Map<Menu, Integer> order = findMenu(menuList, amountList);
        return order;
    }

    private void validateNormalInput(List<String> orderList, List<String> menuList, List<Integer> amountList) {
        for (String orderMenu : orderList) {
            try {
                menuList.add(orderMenu.split("-")[0]);
                amountList.add(Integer.parseInt(orderMenu.split("-")[1]));
                if (containOnlyDrink(menuList)) {
                    throw new IllegalArgumentException();
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new IllegalArgumentException();
            }
        }
    }

    private boolean containOnlyDrink(List<String> menuList) {
        List<String> drinkMenuKoreanName = Menu.getDrinkMenuKoreanName();

        int n = 0;
        for (String s : menuList) {
            if (drinkMenuKoreanName.contains(s)) {
                n++;
            }
        }

        if (n == menuList.size()) {
            return true;
        }
        return false;
    }

    private Map<Menu, Integer> findMenu(List<String> menuList, List<Integer> amountList) {
        Map<Menu, Integer> order = new HashMap<>();
        for (int i = 0; i < menuList.size(); i++) {
            putMenuAmount(order,menuList, amountList, i);
        }

        int sum = order.values().stream().mapToInt(Integer::intValue).sum();
        if (sum > 20) {
            throw new IllegalArgumentException();
        }

        return order;
    }

    private void putMenuAmount(Map<Menu, Integer> order,List<String> menuList, List<Integer> amountList, int i) {
        boolean put = false;
        for (Menu menu : Menu.values()) {
            if(menu.getMenuKoreanName().equals(menuList.get(i)) && amountList.get(i) > 0) {
                validateDuplication(order, menu);
                order.put(menu, amountList.get(i));
                put = true;
                break;
            }
        }
        if (put) {
            return;
        }
        throw new IllegalArgumentException();
    }

    private void validateDuplication(Map<Menu, Integer> order,Menu menu) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException();
        }
    }
}
