package lotto;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.*;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        int inputTry = readInputPrice();
        List<Lotto> lottoList = makeLottoNumber(inputTry);
        List<Integer> correctNumberList= readInputCorrectNumber();
    }

    private static List<Integer> readInputCorrectNumber() {
        List<Integer> correctNumberList = new ArrayList<>();
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String inputCorrectNumber = readLine();
                for (String correctNumber : inputCorrectNumber.split(",")) {
                    int correctNum = Integer.parseInt(correctNumber);
                    validateOverNumber45(correctNum);
                    correctNumberList.add(correctNum);
                }
                validateListSizeIs6(correctNumberList.size(), 6, "[ERROR] 6개의 당첨번호를 입력해주세요");
                return correctNumberList;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validateListSizeIs6(int size, int i, String s) {
        if ((size) != i) {
            throw new IllegalArgumentException(s);
        }
    }

    private static void validateOverNumber45(int correctNum) {
        if (correctNum > 45) {
            throw new IllegalArgumentException("[ERROR] 45이하의 숫자를 선택해주세요");
        }
    }

    private static List<Lotto> makeLottoNumber(int inputTry) {
        List<Lotto> lottoList = new ArrayList<>();
        for (int i = 0; i < inputTry; i++) {
            List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
            Collections.sort(numbers);
            Lotto lotto = new Lotto(numbers);
            lottoList.add(lotto);
        }

        for (Lotto lottonum : lottoList) {
            System.out.println(lottonum.getNumbers());
        }
        return lottoList;
    }

    private static int readInputPrice() {
        int inputTry;
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                int inputPrice = Integer.parseInt(readLine());
                validateThousandUnit(inputPrice);
                inputTry = (inputPrice / 1000);
                return inputTry;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    private static void validateThousandUnit(int inputPrice) {
        validateListSizeIs6(inputPrice % 1000, 0, "[ERROR] 구입금액 단위는 1000원으로 나누어 떨어져야 합니다.");
    }
}
