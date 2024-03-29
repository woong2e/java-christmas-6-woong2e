package christmas;

import java.util.Map;

public class OutputView {
    private final Receipt receipt;

    OutputView(Receipt receipt) {
        this.receipt = receipt;
    }
    public void printEventBenefit() {
        System.out.printf("12월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!\n\n",receipt.getDate());
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
        for (Map.Entry<Menu, Integer> order : receipt.getOrders().entrySet()) {
            System.out.printf("%s %d개\n",order.getKey().getMenuKoreanName(), order.getValue());
        }
        System.out.println();
    }

    public void printTotalOrderPrice() {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(receipt.getTotalOrderPrice() + "원\n");
    }

    public void printGiftMenu() {
        System.out.println("<증정 메뉴>");
        System.out.print(receipt.getGiftMenu().getMenuKoreanName());
        if (receipt.getGiftMenu().getMenuKoreanName().equals("샴페인")) {
            System.out.println(" 1개\n");
            return;
        }
        System.out.println();
        System.out.println();
    }

    public void printBenefitList() {
        System.out.println("<혜택 내역>");
        if (receipt.getBenefitList() == null) {
            System.out.println("없음\n");
            return;
        }

        for (Map.Entry<Event, Integer> benefit : receipt.getBenefitList().entrySet()) {
            if (benefit.getKey().getDiscountKind().equals("크리스마스 디데이 할인")) {
                System.out.printf("%s: %d원\n", benefit.getKey().getDiscountKind(), benefit.getKey().getBenefit() - 100 * (receipt.getDate()-1));
                continue;
            }
            System.out.printf("%s: %d원\n", benefit.getKey().getDiscountKind()
                    , benefit.getKey().getBenefit()*benefit.getValue());
        }
        System.out.println();
    }

    public void printBenefitSum() {
        System.out.println("<총혜택 금액>");
        if (receipt.getBenefitList() == null) {
            System.out.println("0원\n");
            return;
        }
        System.out.println(receipt.getBenefitSum() + "원\n");
    }

    public void printTotalOrderDiscountPrice() {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(receipt.getTotalOrderDiscountPrice() + "원\n");
    }

    public void printDecemberEventBadge() {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(receipt.getDecemberEventBadge());
    }

}
