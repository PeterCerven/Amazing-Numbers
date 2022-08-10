package numbers;

import java.util.ArrayList;

/**
 * Class Logic
 */
public class Logic {

    /**
     * Buzz number - is divisible by 7 or ends with 7
     * @param number long type
     * @return if the number has the property
     */
    public boolean isBuzz(long number) {
        boolean divisible7 = number % 7 == 0;
        boolean endsWith7 = number % 10 == 7;

        return divisible7 || endsWith7;

    }


    /**
     * Duck number - has at least one 0 in non-leading digits
     * @param number long type
     * @return if the number has the property
     */
    public boolean isDuck(long number) {
        String stringNum = String.valueOf(number);
        return stringNum.contains("0") && !stringNum.startsWith("0");
    }

    /**
     * Palindrome number
     * @param number long type
     * @return if the number has the property
     */
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


    /**
     * Gapful number - Number of digits must be 3+ and first and last digit concatenated must divide number with no remainder
     * @param number long type
     * @return if the number has the property
     */
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


    /**
     * Spy number - sum of all digits is equal to the product of all digits
     * @param number long type
     * @return if the number has the property
     */
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


    /**
     * Sunny number - is sunny if N + 1 is perfect square -> can be root is whole number
     * @param number long type
     * @return if the number has the property
     */
    public boolean isSunny(long number) {
        return isSquare(number + 1);
    }

    /**
     * Check if we can parse number to Double
     * @param str parameter in string from
     * @return boolean if the number is numeric
     */
    public boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Square number - Number is perfect square -> can be root is whole number
     * @param number long type
     * @return if the number has the property
     */
    public boolean isSquare(long number) {
        double sqrt = Math.round(Math.sqrt(number));
        return sqrt * sqrt == number;
    }


    /**
     * Jumping number - adjacent digit inside the number differ by 1
     * @param number long type
     * @return if the number has the property
     */
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

    /**
     * Happy number - is a number which eventually reaches 1 when replaced by the sum of the square of each digit
     * @param number long type
     * @return if the number has the property
     */
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

    /**
     * Sad number - is a number which won't reach 1 when replaced by the sum of the square of each digit
     * @param number long type
     * @return if the number has the property
     */
    public boolean isSad(long number) {
        return !isHappy(number);
    }


    /**
     * Creates string representation of number's properties
     * @param number long type
     * @return string representation of number's properties
     */
    public String getProperties(long number) {
        StringBuilder str = new StringBuilder();
        for (PropertiesOfNumbers c :PropertiesOfNumbers.values()) {
            if (c.checkProperty(number)) {
                str.append(" ").append(c.name().toLowerCase()).append(",");
            }
        }
        return str.deleteCharAt(str.length() - 1).toString();
    }


    /**
     * Check if there are mutually exclusive properties in the parameters
     * @param properties list of properties
     * @return empty string if there are no exclusive properties, otherwise returns exclusive properties
     */
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

    /**
     * Checks if the numbers has all the needed properties
     * @param number long type
     * @param properties list of properties
     * @return if the number has all properties
     */
    public boolean containsProperties(long number, ArrayList<String> properties) {
        int count = properties.size();
        for (PropertiesOfNumbers c :PropertiesOfNumbers.values()) {
            if (properties.contains(c.name()) && c.checkProperty(number)) {
                count--;
            }
            if (properties.contains("-" + c.name()) && !c.checkProperty(number)) {
                count--;
            }
        }

        return count == 0;
    }
}
