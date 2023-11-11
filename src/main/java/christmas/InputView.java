package christmas;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    private static final String INVALID_ORDER = "[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public int readVisitDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        while (true) {
            try {
                String input = Console.readLine();
                int date = validateDate(input);
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int validateDate(String date) {
        int n = validateNumber(date);
        validateRange(n);
        return n;
    }

    private int validateNumber(String date) {
        try {
            int n = Integer.parseInt(date);
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    public Map<Menu, Integer> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        Map<Menu, Integer> order;
        while (true) {
            try {
                String input = Console.readLine();
                order = splitInput(input);
                return order;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
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
                    throw new IllegalArgumentException(INVALID_ORDER);
                }
            } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                throw new IllegalArgumentException(INVALID_ORDER);
            }
        }
    }

    private boolean containOnlyDrink(List<String> menuList) {
        List<String> drinkMenuKoreanName = Menu.getDrinkMenuKoreanName();
        for (String s : menuList) {
            if (drinkMenuKoreanName.contains(s)) {
                continue;
            }
            return false;
        }
        return true;
    }

    private Map<Menu, Integer> findMenu(List<String> menuList, List<Integer> amountList) {
        Map<Menu, Integer> order = initOrder();
        for (int i = 0; i < menuList.size(); i++) {
            order = putMenuAmount(menuList, amountList, i);
        }
        return order;
    }

    private Map<Menu, Integer> putMenuAmount(List<String> menuList, List<Integer> amountList, int i) {
        Map<Menu, Integer> order = initOrder();
        for (Menu menu : Menu.values()) {
            if(menu.getMenuKoreanName().equals(menuList.get(i)) && amountList.get(i) > 0) {
                validateDuplication(order, menu);
                order.put(menu, amountList.get(i));
                continue;
            }
            throw new IllegalArgumentException(INVALID_ORDER);
        }
        int sum = order.values().stream().mapToInt(Integer::intValue).sum();
        if (sum > 20) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
        return order;
    }

    private void validateDuplication(Map<Menu, Integer> order,Menu menu) {
        if (order.containsKey(menu)) {
            throw new IllegalArgumentException(INVALID_ORDER);
        }
    }



    private Map<Menu, Integer> initOrder() {
        Map<Menu, Integer> order = new HashMap<>();

        for (Menu menu : Menu.values()) {
            order.put(menu, 0);
        }
        return order;
    }


}
