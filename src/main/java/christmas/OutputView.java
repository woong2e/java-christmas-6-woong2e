package christmas;

import java.util.Map;

public class OutputView {
    private Receipt receipt;

    OutputView(Receipt receipt) {
        this.receipt = receipt;
    }
    public void printEventBenefit() {

        printOrderMenu();
        printTotalOrderPrice();
        printGiftMenu();
        printBenefitList();
        printBenefitSum();
        printTotalOrderDiscountPrice();
        printDecemberEventBadge();
    }

    public void printOrderMenu() {
        System.out.println("<주문 메뉴>");
        Map<Menu, Integer> orders = receipt.getOrders();
        for (Map.Entry<Menu, Integer> order : orders.entrySet()) {
            System.out.printf("%s %d개\n",order.getKey().getMenuKoreanName(), order.getValue());
        }
    }

    public void printTotalOrderPrice() {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(receipt.getTotalOrderPrice() + "원");
    }

    public void printGiftMenu() {
        System.out.println("<증정 메뉴>");
        Menu giftMenu = receipt.getGiftMenu();
        System.out.println(giftMenu.getMenuKoreanName());

    }

    public void printBenefitList() {
        System.out.println("<혜택 내역>");
        Map<Event, Integer> benefitList = receipt.getBenefitList();
        for (Map.Entry<Event, Integer> benefit : benefitList.entrySet()) {
            System.out.printf("%s: %d원", benefit.getKey().getDiscountKind()
                    , benefit.getKey().getBenefit()*benefit.getValue());
        }
    }

    public void printBenefitSum() {
        System.out.println("<총혜택 금액>");
        System.out.println(receipt.getBenefitSum());
    }

    public void printTotalOrderDiscountPrice() {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(receipt.getTotalOrderDiscountPrice());
    }

    public void printDecemberEventBadge() {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(receipt.getDecemberEventBadge());
    }

}
