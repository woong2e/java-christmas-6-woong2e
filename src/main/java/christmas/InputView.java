package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String INVALID_DATE = "[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.";

    public void printHello() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }

    public int askVisitDate() {
        while (true) {
            try {
                int date = validate(Console.readLine());
                return date;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private int validate(String date) {
        int n = validateNumber(date);
        validateRange(n);
        return n;
    }

    private int validateNumber(String date) {
        try {
            int n = Integer.parseInt(date);
            return n;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }

    private void validateRange(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException(INVALID_DATE);
        }
    }


}
