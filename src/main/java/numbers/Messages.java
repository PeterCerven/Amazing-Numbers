package numbers;

import java.util.ArrayList;

public class Messages {
    Logic logic;

    public Messages() {
        logic = new Logic();
    }

    public void availableProperties() {
        System.out.println("Available properties: [EVEN, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY," +
                " JUMPING, HAPPY, SAD]\n");
    }

    public void errorFirstParameter() {
        System.out.println("The first parameter should be a natural number or zero.");
    }

    public void errorSecondParameter() {
        System.out.println("The second parameter should be a natural number.");
    }


    public void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameters show how many consecutive numbers are to be processed;");
        System.out.println("- two natural numbers and a property to search for;");
        System.out.println("- two natural numbers and two properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.\n");
    }

    public void errorMutuallyExclusive(String property) {
        System.out.println("The request contains mutually exclusive properties: " + property);
        System.out.println("There are no numbers with these properties.\n");
    }

    public void errorWrongProperties(ArrayList<String> properties, int num) {
        StringBuilder str = new StringBuilder();
        if (num == 1) {
            str.append("The property [");
        } else {
            str.append("The properties [");
        }
        for (String string : properties) {
            if (!logic.contains(string)) {
                str.append(string).append(", ");
            }
        }
        str.deleteCharAt(str.length() - 1);
        str.deleteCharAt(str.length() - 1);
        if (num == 1) {
            str.append("] is wrong.");
        } else {
            str.append("] are wrong.");
        }

        System.out.println(str);

        availableProperties();
    }
}
