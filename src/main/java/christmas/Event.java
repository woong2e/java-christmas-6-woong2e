package christmas;

import java.util.List;

public enum Event {
    CHRISTMAS_D_DAY_DISCOUNT(List.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25), "total", 1_000),
    WEEKDAY_DISCOUNT(List.of(
            3, 4, 5, 6, 7,
            10, 11, 12, 13, 14,
            17, 18, 19, 20, 21,
            24, 25, 26, 27, 28, 31), "dessert", 2_023),
    WEEKEND_DISCOUNT(List.of(
            1, 2, 8, 9, 15, 16,
            22, 23, 29, 30), "main", 2_023),
    SPECIAL_DISCOUNT(List.of(
            3, 10, 17, 24, 25, 31), "total", 1_000),
    PRESENTATION_EVENT(List.of(
            1, 2, 3, 4, 5,
            6, 7, 8, 9, 10,
            11, 12, 13, 14, 15,
            16, 17, 18, 19, 20,
            21, 22, 23, 24, 25,
            26, 27, 28, 29, 30,
            31), "drink", 25_000);

    private List<Integer> date;
    private String item;
    private int benefit;

    Event(List<Integer> date, String item, int benefit) {
        this.date = date;
        this.item = item;
        this.benefit = benefit;
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
