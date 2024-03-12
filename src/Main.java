import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                break;
            }
            String result = calc(input);
            System.out.println(result);
        }
    }

    public static String calc(String input) throws Exception {
        String[] values = input.split(" ");

        if (values.length != 3) {
            throw new Exception("Ошибка ввода. Калькулятор может обрабатывать только два числа!");
        }

        String operator = values[1];
        int firstNumber = 0;
        int secondNumber = 0;
        String result;
        boolean isNumberRoman = false;



        if (Roman.romanNumberCheck(values[0]) && Roman.romanNumberCheck(values[2])) {
            firstNumber = Roman.convertToArabian(values[0]);
            secondNumber = Roman.convertToArabian(values[2]);
            isNumberRoman = true;
        }
        else if (!Roman.romanNumberCheck(values[0]) && !Roman.romanNumberCheck(values[2])) {
            firstNumber = Integer.parseInt(values[0]);
            secondNumber = Integer.parseInt(values[2]);
            isNumberRoman = false;
        }
        else {
            throw new Exception ("Ошибка ввода. Необходимо вводить один формат чисел!");
        }

        if (firstNumber > 10 || secondNumber > 10) {
            throw new Exception ("Ошибка ввода. Калькулятор может обрабатывать только два числа до 10!");
        }
        int resultAfterMathOperation = mathOperation(firstNumber, secondNumber, operator);
        if (isNumberRoman) {
            if (resultAfterMathOperation <= 0) {
                throw new Exception ("Ошибка ввода. Ответ не может быть меньше нуля!");
            }
            result = Roman.convertToRoman(resultAfterMathOperation);
        } else {
            result = String.valueOf(resultAfterMathOperation);
        }
        return result;
    }

    static int mathOperation(int a, int b, String operation) throws Exception{
        switch (operation){
            case("+"):
                return a + b;
            case("-"):
                return a - b;
            case("*"):
                return a * b;
            case("/"):
                if (b != 0) {
                    return a / b;
                }
                else
                {
                    throw new Exception("Ошибка ввода. Делить на ноль невозможно!");
                }
            default:
                throw new Exception("Ошибка ввода. Несуществующая операция!");
        }
    }
}

class Roman {
    static String[] romanNumbers = new String[]{"i", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"
    };

    public static boolean romanNumberCheck(String value) {
        for (String romanNumber : romanNumbers) {
            if (value.equals(romanNumber)) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanNumbers.length; i++) {
            if (roman.equals(romanNumbers[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanNumbers[arabian];
    }
}
