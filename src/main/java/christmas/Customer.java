package christmas;

import java.util.Map;

public class Customer {

    public static void book() {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        Order order = new Order();

        inputView.printHello();
        int date = inputView.readVisitDate();
        Map<Menu, Integer> orders = order.check(inputView.readMenu());
        outputView.printEventBenefit(date, orders);
    }
}
