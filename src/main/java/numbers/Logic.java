package numbers;

import java.util.ArrayList;

public class Logic {

    //divisible by 7 or ends with 7
    public boolean isBuzz(long number) {
        boolean divisible7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;

        return divisible7 || endsWith7;

    }

    //has at least one 0 in non-leading digits
    public boolean isDuck(long number) {
        String stringNum = String.valueOf(number);
        return stringNum.contains("0") && !stringNum.startsWith("0");
    }

    public boolean isPalindrome(long number) {
        String stringNum = String.valueOf(number);
        int size = stringNum.length();
        for (int i = 0; i < size / 2; i++) {
            if (stringNum.charAt(i) != stringNum.charAt(size - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //Number of digits must be 3+ and first and last digit concatenated must divide number with no remainder
    public boolean isGapful(long number) {
        String stringNum = String.valueOf(number);
        if (stringNum.length() < 3) {
            return false;
        }
        int concatenation = Integer.parseInt(stringNum.charAt(0) + "" + stringNum.charAt(stringNum.length() - 1));

        return number % concatenation == 0;
    }

    public boolean isEven(long number) {
        return number % 2 == 0;
    }

    public boolean isOdd(long number) {
        return number % 2 == 1;
    }

    //sum of all digits is equal to the product of all digits
    public boolean isSpy(long number) {
        int sum = 0;
        int product = 1;
        int digit;
        String str = String.valueOf(number);
        String[] digits = str.split("");
        for (String s : digits) {
            digit = Integer.parseInt(s);
            sum += digit;
            product *= digit;
        }

        return sum == product;
    }

    //N is sunny if N + 1 is perfect square -> can be root is whole number
    public boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean isSquare(long number) {
        double sqrt = Math.round(Math.sqrt(number));
        return sqrt * sqrt == number;
    }

    //adjacent digit inside the number differ by 1
    public boolean isJumping(long number) {
        String[] digits = String.valueOf(number).split("");
        if (digits.length == 1) {
            return true;
        }

        for (int i = 1; i < digits.length; i++) {
            if (Math.abs(Long.parseLong(digits[i]) - Long.parseLong(digits[i - 1])) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean isHappy(long number) {
        ArrayList<Long> existed = new ArrayList<>();
        existed.add(1L);
        long sum;
        String[] digits = String.valueOf(number).split("");
        while (true) {
            sum = 0;
            for (String digit : digits) {
                long num = Integer.parseInt(digit);
                sum += num * num;
            }
            if (existed.contains(sum)) {
                break;
            }
            existed.add(sum);
            digits = String.valueOf(sum).split("");
        }
        return sum == 1;
    }

    public boolean isSad(long number) {
        return !isHappy(number);
    }


    public String getProperties(long number) {
        StringBuilder str = new StringBuilder();
        if (isBuzz(number)) {
            str.append(" buzz,");
        }
        if (isDuck(number)) {
            str.append(" duck,");
        }
        if (isPalindrome(number)) {
            str.append(" palindromic,");
        }
        if (isGapful(number)) {
            str.append(" gapful,");
        }
        if (isSpy(number)) {
            str.append(" spy,");
        }
        if (isSquare(number)) {
            str.append(" square,");
        }
        if (isSunny(number)) {
            str.append(" sunny,");
        }
        if (isJumping(number)) {
            str.append(" jumping,");
        }
        if (isHappy(number)) {
            str.append(" happy,");
        }
        if (isSad(number)) {
            str.append(" sad,");
        }
        if (isEven(number)) {
            str.append(" even,");
        }
        if (isOdd(number)) {
            str.append(" odd,");
        }
        return str.deleteCharAt(str.length() - 1).toString();
    }

    public String mutuallyExclusive(ArrayList<String> properties) {
        for (PropertiesOfNumbers types : PropertiesOfNumbers.values()) {
            if (properties.contains(types.name()) && properties.contains(types.getOpposite())) {
                return "[" + types.name() + "," + types.getOpposite() + "]";
            }
            if (properties.contains(types.name()) && properties.contains(types.getExclusive())) {
                return "[" + types.name() + "," + types.getExclusive() + "]";
            }
            if (properties.contains(types.getOpposite()) && properties.contains(types.getOppositeExclusive())) {
                return "[" + types.getOpposite() + "," + types.getOppositeExclusive() + "]";
            }
        }

        return "";
    }

    public boolean contains(String property) {
        for (PropertiesOfNumbers types : PropertiesOfNumbers.values()) {
            if (types.name().equals(property)) {
                return true;
            }
        }
        return false;
    }

    public boolean containsProperties(long number, ArrayList<String> properties) {
        int count = 0;
        for (String property : properties) {
            switch (property) {
                case "BUZZ":
                    if (isBuzz(number)) {
                        count++;
                    }
                    break;

                case "DUCK":
                    if (isDuck(number)) {
                        count++;
                    }
                    break;

                case "PALINDROMIC":
                    if (isPalindrome(number)) {
                        count++;
                    }
                    break;

                case "GAPFUL":
                    if (isGapful(number)) {
                        count++;
                    }

                    break;

                case "SPY":
                    if (isSpy(number)) {
                        count++;
                    }
                    break;

                case "SQUARE":
                    if (isSquare(number)) {
                        count++;
                    }
                    break;

                case "SUNNY":
                    if (isSunny(number)) {
                        count++;
                    }
                    break;

                case "JUMPING":
                    if (isJumping(number)) {
                        count++;
                    }
                    break;

                case "HAPPY":
                    if (isHappy(number)) {
                        count++;
                    }
                    break;

                case "SAD":
                    if (isSad(number)) {
                        count++;
                    }
                    break;

                case "EVEN":
                    if (isEven(number)) {
                        count++;
                    }
                    break;

                case "ODD":
                    if (isOdd(number)) {
                        count++;
                    }
                    break;
                case "-BUZZ":
                    if (!isBuzz(number)) {
                        count++;
                    }
                    break;

                case "-DUCK":
                    if (!isDuck(number)) {
                        count++;
                    }
                    break;

                case "-PALINDROMIC":
                    if (!isPalindrome(number)) {
                        count++;
                    }
                    break;

                case "-GAPFUL":
                    if (!isGapful(number)) {
                        count++;
                    }

                    break;

                case "-SPY":
                    if (!isSpy(number)) {
                        count++;
                    }
                    break;

                case "-SQUARE":
                    if (!isSquare(number)) {
                        count++;
                    }
                    break;

                case "-SUNNY":
                    if (!isSunny(number)) {
                        count++;
                    }
                    break;

                case "-JUMPING":
                    if (!isJumping(number)) {
                        count++;
                    }
                    break;

                case "-HAPPY":
                    if (!isHappy(number)) {
                        count++;
                    }
                    break;

                case "-SAD":
                    if (!isSad(number)) {
                        count++;
                    }
                    break;

                case "-EVEN":
                    if (!isEven(number)) {
                        count++;
                    }
                    break;

                case "-ODD":
                    if (!isOdd(number)) {
                        count++;
                    }
                    break;
            }

        }
        return count == properties.size();
    }
}
