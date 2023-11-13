package christmas;

import java.util.List;

public enum Event {
    CHRISTMAS_D_DAY_DISCOUNT("크리스마스 디데이 할인",
            List.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25), "total", -1_000),

    WEEKDAY_DISCOUNT("평일 할인",
            List.of(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28, 31), "dessert", -2_023),

    WEEKEND_DISCOUNT("주말 할인",
            List.of(
            1, 2, 8, 9, 15, 16,
            22, 23, 29, 30), "main", -2_023),

    SPECIAL_DISCOUNT("특별 할인",
            List.of(
            3, 10, 17, 24, 25, 31), "total", -1_000),

    PRESENTATION_EVENT("증정 이벤트",
            List.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25,
            26, 27, 28, 29, 30,
            31), "drink", -25_000);

    private String discountKind;
    private List<Integer> date;
    private String item;
    private int benefit;

    Event(String discountKind, List<Integer> date, String item, int benefit) {
        this.discountKind = discountKind;
        this.date = date;
        this.item = item;
        this.benefit = benefit;
    }

    public String getDiscountKind() {
        return discountKind;
    }

    public List<Integer> getDate() {
        return date;
    }
    public String getItem() {
        return item;
    }

    public int getBenefit() {
        return benefit;
    }

}
