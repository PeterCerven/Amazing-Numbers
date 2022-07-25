package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class UserInterface {
    private final Scanner scanner;
    private long firstNum;
    private long secondNum;
    private final Logic logic;
    private final Messages messages;
    private String[] parts;

    public UserInterface() {
        scanner = new Scanner(System.in);
        logic = new Logic();
        messages = new Messages();
        start();
    }

    public void start() {
        System.out.println("Welcome to Amazing Numbers!\n");
        messages.printInstructions();


        while (true) {
            System.out.print("Enter a request: ");
            parts = scanner.nextLine().trim().toUpperCase().split(" ");
            System.out.println("");


            switch (parts.length) {
                case 0:
                    zeroParameters();
                    break;
                case 1:
                    if (oneParameter()) {
                        return;
                    }
                    break;
                case 2:
                    twoParameters();
                    break;
            }
            if (parts.length >= 3) {
                threeOrMoreParameters();
            }

        }
    }

    private void zeroParameters() {
        messages.printInstructions();
    }

    private boolean oneParameter() {
        this.firstNum = Long.parseLong(parts[0]);
        if (!(isValid(firstNum))) {
            messages.errorFirstParameter();
            return false;
        }
        if (firstNum == 0) {
            System.out.println("Goodbye!");
            return true;
        }
        printOneNum();
        return false;
    }

    private void twoParameters() {
        this.firstNum = Long.parseLong(parts[0]);
        this.secondNum = Long.parseLong(parts[1]);
        if (!(isValid(firstNum))) {
            messages.errorFirstParameter();
            return;
        }
        if (!(isValid(secondNum))) {
            messages.errorSecondParameter();
            return;
        }
        printMultipleNums(secondNum);
    }

    private void threeOrMoreParameters() {
        this.firstNum = Long.parseLong(parts[0]);
        this.secondNum = Long.parseLong(parts[1]);
        ArrayList<String> parameters = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(parts, 2, parts.length)));



        if (!(isValid(firstNum))) {
            messages.errorFirstParameter();
            return;
        }

        if (!(isValid(secondNum))) {
            messages.errorSecondParameter();
            return;
        }
        int badProperties = validProperties(parameters);
        if (!(badProperties == 0)) {
            messages.errorWrongProperties(parameters, badProperties);
            return;
        }
        String state = logic.mutuallyExclusive(parameters);
        if (!state.isEmpty()) {
            messages.errorMutuallyExclusive(state);
            return;
        }

        printMultipleNumsWithProperties(secondNum, parameters);
    }


    private void printMultipleNumsWithProperties(long times, ArrayList<String> properties) {
        int count = 0;
        long currentNum = firstNum;
        while (count < times) {
            if (logic.containsProperties(currentNum, properties)) {
                System.out.format("%16d", currentNum);
                System.out.println(" is" + logic.getProperties(currentNum));
                count++;
            }
            currentNum++;

        }
        System.out.println("");
    }

    private int validProperties(ArrayList<String> properties) {
        int counter = 0;

        for (PropertiesOfNumbers type : PropertiesOfNumbers.values()) {
            for (String string : properties){
                if (string.equals(type.name()) || string.equals(type.getOpposite())){
                    counter++;
                }
            }

        }
        return properties.size() - counter;
    }


    private boolean isValid(long num) {
        return num >= 0;
    }

    public void printOneNum() {
        String form = String.format("%,d", firstNum);
        System.out.println("Properties of " + form);
        System.out.println("        buzz: " + logic.isBuzz(firstNum));
        System.out.println("        duck: " + logic.isDuck(firstNum));
        System.out.println(" palindromic: " + logic.isPalindrome(firstNum));
        System.out.println("      gapful: " + logic.isGapful(firstNum));
        System.out.println("         spy: " + logic.isSpy(firstNum));
        System.out.println("      square: " + logic.isSquare(firstNum));
        System.out.println("       sunny: " + logic.isSunny(firstNum));
        System.out.println("     jumping: " + logic.isJumping(firstNum));
        System.out.println("       happy: " + logic.isHappy(firstNum));
        System.out.println("         sad: " + logic.isSad(firstNum));
        System.out.println("        even: " + logic.isEven(firstNum));
        System.out.println("         odd: " + logic.isOdd(firstNum));
        System.out.println("");
    }

    public void printMultipleNums(long times) {
        for (int i = 0; i < times; i++) {
            System.out.format("%16d", (firstNum + i));
            System.out.println(" is" + logic.getProperties(firstNum + i));
        }
        System.out.println("");
    }
}
