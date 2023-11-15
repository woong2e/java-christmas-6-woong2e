package christmas;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


class OrderTest {

    @Test
    void 메뉴_split_테스트() {
        final Order order = new Order();
        final String input = "해산물파스타-2";

        Map<Menu, Integer> orders =  order.check(input);

        for(Map.Entry<Menu, Integer> ord : orders.entrySet()) {
            assertThat(ord.getKey().getMenuKoreanName()).isEqualTo("해산물파스타");
            assertThat(ord.getValue()).isEqualTo(2);
        }
    }

    @Test
    void 메뉴_중복_테스트() {
        final Order order = new Order();
        final String input = "해산물파스타-2,해산물파스타-2";

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> order.check(input));

        assertThat(exception.getMessage()).isEqualTo("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @Test
    void 메뉴_음료만_포함되어있는지_테스트() {
        final Order order = new Order();
        final String input = "레드와인-10";

        final RuntimeException exception = assertThrows(RuntimeException.class, () -> order.check(input));

        assertThat(exception.getMessage()).isEqualTo("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

}