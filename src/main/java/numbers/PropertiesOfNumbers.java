package numbers;

public enum PropertiesOfNumbers {
    BUZZ("-BUZZ", "", ""),
    DUCK("-DUCK", "SPY", ""),
    PALINDROMIC("-PALINDROMIC", "", ""),
    GAPFUL("-GAPFUL", "", ""),
    SPY("-SPY", "DUCK", ""),
    SQUARE("-SQUARE", "SUNNY", ""),
    SUNNY("-SUNNY", "SQUARE", ""),
    JUMPING("-JUMPING", "", ""),
    HAPPY("-HAPPY", "SAD", "-SADenum"),
    SAD("-SAD", "HAPPY", "-HAPPY"),
    EVEN("-EVEN", "ODD", "-ODD"),
    ODD("-ODD", "EVEN", "-EVEN");



    private final String opposite;
    private final String exclusive;
    private final String oppositeExclusive;

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
