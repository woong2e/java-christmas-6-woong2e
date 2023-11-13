package christmas;

import java.util.Map;

public class Customer {

    public static void book() {
        InputView inputView = new InputView();

        int date;
        String menu;
        Order order = new Order();
        Map<Menu, Integer> orders;
        while (true) {
            try {
                date = inputView.readVisitDate();
                menu = inputView.readMenu();
                orders = order.check(menu);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        Receipt receipt = new Receipt(date, orders);
        OutputView outputView = new OutputView(receipt);
        outputView.printEventBenefit();
    }
}
