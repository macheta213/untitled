import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws Exception {
        for (;;){
            Scanner scanner = new Scanner(System.in);

            System.out.print("Input: ");
            String input = scanner.nextLine();

            if (Objects.equals(input, "0")) {
                break;
            }

            String result = validation(input);

            System.out.println(result);
        }
    }

    private static String validation(String input) throws Exception {
        String operator = null;
        input = input.replaceAll("\\s", "");
        String result;

        if (input.contains("+")) {
            operator = "+";
        } else if (input.contains("-")) {
            operator = "-";
        } else if (input.contains("/")) {
            operator = "/";
        } else if (input.contains("*")) {
            operator = "*";
        }

        if (operator == null) {
            throw new Exception("1");
        }

        String[] words = input.split(Pattern.quote(operator));

        if (words.length != 2) {
            throw new Exception("2");
        }

        result = RimOrArab(words, operator);

        return result;
    }

    public static int romanToArabic(String romanNumeral) {
        HashMap<Character, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put('I', 1);
        romanNumerals.put('V', 5);
        romanNumerals.put('X', 10);
        romanNumerals.put('L', 50);

        int arabicNumber = 0;
        int previousValue = 0;

        for (int i = romanNumeral.length() - 1; i >= 0; i--) {
            int currentValue = romanNumerals.get(romanNumeral.charAt(i));

            if (currentValue >= previousValue) {
                arabicNumber += currentValue;
            } else {
                arabicNumber -= currentValue;
            }

            previousValue = currentValue;
        }

        return arabicNumber;
    }

    private static String RimOrArab(String[] words, String operator) throws
            Exception {
        String first = words[0];
        String second = words[1];
        String result = "";

        if (rim(first) && rim(second)) {
            int f = romanToArabic(first);
            int s = romanToArabic(second);
            if (Objects.equals(operator, "+")) {
                result = String.valueOf(f + s);
            } else if (Objects.equals(operator, "-")) {
                result = String.valueOf(f - s);
            } else if (Objects.equals(operator, "/")) {
                result = String.valueOf(f / s);
            } else if (Objects.equals(operator, "*")) {
                result = String.valueOf(f * s);
            }
            if (Integer.parseInt(result) < 1 || Integer.parseInt(result) >
                    99) {
                throw new Exception("3");
            }
            result = convertToRoman(result);
        } else if (arab(first) && arab(second)) {
            if (Objects.equals(operator, "+")) {
                result = String.valueOf(Integer.parseInt(first) +
                        Integer.parseInt(second));
            } else if (Objects.equals(operator, "-")) {
                result = String.valueOf(Integer.parseInt(first) -
                        Integer.parseInt(second));
            } else if (Objects.equals(operator, "/")) {
                result = String.valueOf(Integer.parseInt(first) /
                        Integer.parseInt(second));
            } else if (Objects.equals(operator, "*")) {
                result = String.valueOf(Integer.parseInt(first) *
                        Integer.parseInt(second));
            }
        } else {
            throw new Exception("4");
        }
        return result;
    }



    static boolean arab(String num) {
        try {
            Integer.parseInt(num);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String convertToRoman(String number) {
        int[] arabicValues = {50, 40, 10, 9, 5, 4, 1};
        String[] romanSymbols = {"L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder romanNumeral = new StringBuilder();

        int num = Integer.parseInt(number);

        for (int i = 0; i < arabicValues.length; i++) {
            while (num >= arabicValues[i]) {
                romanNumeral.append(romanSymbols[i]);
                num -= arabicValues[i];
            }
        }

        return romanNumeral.toString();
    }

    static boolean rim(String num) {
        boolean flag = true;
        for (int i = 0; i < num.length(); i++) {
            if (!(num.charAt(i) == 'I' || num.charAt(i) == 'V' ||
                    num.charAt(i) == 'X' || num.charAt(i) == 'L')) {
                flag = false;
                break;
            }
        }
        return flag;
    }
}