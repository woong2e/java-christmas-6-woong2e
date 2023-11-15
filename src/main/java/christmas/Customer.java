package christmas;

import java.util.Map;

public class Customer {
    private static int date;
    private static Map<Menu, Integer> orders;
    public static void book() {
        InputView inputView = new InputView();
        Order order = new Order();
        validateInput(inputView, order);
        Receipt receipt = new Receipt(date, orders);
        OutputView outputView = new OutputView(receipt);
        outputView.printEventBenefit();
    }

    private static void validateInput(InputView inputView, Order order) {
        while (true) {
            try {
                date = inputView.readVisitDate();
                String menu = inputView.readMenu();
                orders = order.check(menu);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
