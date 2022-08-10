package numbers;

/**
 * Enums with all their respective properties, checks and exclusive properties
 */
public enum PropertiesOfNumbers {
    BUZZ("-BUZZ","",""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isBuzz(number);
        }
    },
    DUCK("-DUCK", "SPY", ""){
        @Override
        public boolean checkProperty(long number) {
            return  logic.isDuck(number);
        }
    },
    PALINDROMIC("-PALINDROMIC", "", ""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isPalindrome(number);
        }
    },
    GAPFUL("-GAPFUL", "", ""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isGapful(number);
        }
    },
    SPY("-SPY", "DUCK", ""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isSpy(number);
        }
    },
    SQUARE("-SQUARE", "SUNNY", ""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isSquare(number);
        }
    },
    SUNNY("-SUNNY", "SQUARE", ""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isSunny(number);
        }
    },
    JUMPING("-JUMPING", "", ""){
        @Override
        public boolean checkProperty(long number) {
            return logic.isJumping(number);
        }
    },
    HAPPY("-HAPPY", "SAD", "-SAD"){
        @Override
        public boolean checkProperty(long number) {
            return logic.isHappy(number);
        }
    },
    SAD("-SAD", "HAPPY", "-HAPPY"){
        @Override
        public boolean checkProperty(long number) {
            return logic.isSad(number);
        }
    },
    EVEN("-EVEN", "ODD", "-ODD"){
        @Override
        public boolean checkProperty(long number) {
            return logic.isEven(number);
        }
    },
    ODD("-ODD", "EVEN", "-EVEN"){
        @Override
        public boolean checkProperty(long number) {
            return logic.isOdd(number);
        }
    };



    private final String opposite;
    private final String exclusive;
    private final String oppositeExclusive;
    public abstract boolean checkProperty(long number);
    public static final Logic logic = new Logic();



    PropertiesOfNumbers(String opposite, String exclusive, String oppositeExclusive) {
        this.opposite = opposite;
        this.exclusive = exclusive;
        this.oppositeExclusive = oppositeExclusive;
    }

    public String getOpposite() {
        return opposite;
    }

    public String getExclusive() {
        return exclusive;
    }

    public String getOppositeExclusive() {
        return oppositeExclusive;
    }


}
