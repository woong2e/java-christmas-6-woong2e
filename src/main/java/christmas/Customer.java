package christmas;

import java.util.Map;

public class Customer {

    public static void book() {
        InputView inputView = new InputView();

        inputView.printHello();
        int date = inputView.readVisitDate();
        Order order = new Order();
        Map<Menu, Integer> orders = order.check(inputView.readMenu());
        Receipt receipt = new Receipt(date, orders);
        OutputView outputView = new OutputView(receipt);
        outputView.printEventBenefit();
    }
}
