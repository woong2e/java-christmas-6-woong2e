package christmas;

import java.util.Map;

public class Customer {
    private static int date;
    private static String menu;
    private static Map<Menu, Integer> orders;
    public static void book() {
        InputView inputView = new InputView();
        Order order = new Order();
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
