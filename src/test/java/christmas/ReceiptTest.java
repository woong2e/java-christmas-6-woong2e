package christmas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ReceiptTest {

    @Test
    void 영수증_할인_전_총주문_금액_테스트() {
        final Order order = new Order();
        final String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        final Receipt receipt = new Receipt(3, order.check(input));

        assertThat(receipt.getTotalOrderPrice()).isEqualTo(142_000);
    }

    @Test
    void 증정_메뉴_테스트() {
        final Order order = new Order();
        final String input1 = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";
        final String input2 = "티본스테이크-1";

        final Receipt receipt1 = new Receipt(3, order.check(input1));
        final Receipt receipt2 = new Receipt(3, order.check(input2));

        assertThat(receipt1.getGiftMenu()).isEqualTo(Menu.CHAMPAGNE);
        assertThat(receipt2.getGiftMenu()).isEqualTo(Menu.NON);
    }

    @Test
    void 혜택_내역_리스트_테스트() {
        final Order order = new Order();
        final String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        final Receipt receipt = new Receipt(3, order.check(input));

        List<String> inputDiscount = new ArrayList<>();
        List<String> inputNum = new ArrayList<>();
        for (Map.Entry<Event, Integer> benefit : receipt.getBenefitList().entrySet()) {
            inputDiscount.add(benefit.getKey().getDiscountKind());
            inputNum.add(benefit.getValue().toString());
        }
        assertThat(inputDiscount).contains("크리스마스 디데이 할인",
                "평일 할인", "특별 할인", "증정 이벤트");
        assertThat(inputNum).contains("1", "2");
    }

    @Test
    void 총혜택_금액_테스트() {
        final Order order = new Order();
        final String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        final Receipt receipt = new Receipt(3, order.check(input));

        assertThat(receipt.getBenefitSum()).isEqualTo(-31246);
    }

    @Test
    void 할인_후_예상_결제_금액_테스트() {
        final Order order = new Order();
        final String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        final Receipt receipt = new Receipt(3, order.check(input));

        assertThat(receipt.getTotalOrderDiscountPrice()).isEqualTo(135_754);
    }

    @Test
    void _12월_이벤트_배지_테스트() {
        final Order order = new Order();
        final String input = "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1";

        final Receipt receipt = new Receipt(3, order.check(input));

        assertThat(receipt.getDecemberEventBadge()).isEqualTo("산타");
    }
}