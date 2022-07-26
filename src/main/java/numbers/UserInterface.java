package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

/**
 * Class for entering request and getting output
 */
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

    /**
     * Prints numbers that many times as is specified with all the properties that the number must have starting
     * from the given number
     * @param times how many numbers to print
     * @param properties all the properties that need to be present in the number
     */
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


    /**
     * Check if the properties are valid
     * @param properties list parameters that are checked
     * @return number of wrong properties
     */
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

    /**
     * Prints one number and all the properties and if they are present in the number
     */
    public void printOneNum() {
        String form = String.format(Locale.US, "%,d", firstNum);
        System.out.println("Properties of " + form);
        for (PropertiesOfNumbers c :PropertiesOfNumbers.values()) {
            System.out.printf("%12s: %b\n", c.name().toLowerCase(), c.checkProperty(firstNum));
        }
    }

    /**
     * Prints multiple numbers and all the properties and if they are present in the number
     */
    public void printMultipleNums(long times) {
        for (int i = 0; i < times; i++) {
            System.out.format(Locale.US, "%,16d", (firstNum + i));
            System.out.println(" is" + logic.getProperties(firstNum + i));
        }
        System.out.println("");
    }
}
