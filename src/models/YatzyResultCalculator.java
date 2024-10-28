package models;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {
    private final Die[] dice;
    private final int[] sum = new int[6];

    /**
     *
     * @param dice
     * Calculate score of throws with 5 dice.
     */
    public YatzyResultCalculator(Die[] dice) {
        this.dice = dice;
        for (Die die : dice) {
            sum[die.getRoll() - 1]++;
        }
    }

    /**
     * Calculates the score for Yatzy uppersection
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        return sum[eyes - 1] * eyes;
    }

    public int onePairScore() {
        for (int i = 5; i >= 0; i--) {
            if (sum[i] >= 2) {
                return (i + 1) * 2;
            }
        }
        return 0;
    }

    public int twoPairScore() {
        int twoPairScore = 0;
        int pairCount = 0;
        for (int i = 5; i >= 0; i--) {
            if (sum[i] >= 2) {
                twoPairScore += (i + 1) * 2;
                pairCount++;
            }
        }
        if (pairCount == 2) {
            return twoPairScore;
        }
        return 0;
    }

    public int threeOfAKindScore() {
        for (int i = 5; i >= 0; i--) {
            if (sum[i] >= 3) {
                return (i + 1) * 3;
            }
        }
        return 0;
    }

    public int fourOfAKindScore() {
        for (int i = 5; i >= 0; i--) {
            if (sum[i] >= 4) {
                return (i + 1) * 4;
            }
        }
        return 0;
    }

    public int smallStraightScore() {
        for (int i = 0; i < 5; i++) {
            if (sum[i] != 1) {
                return 0;
            }
        }
        return 15;
    }

    public int largeStraightScore() {
        for (int i = 5; i > 0; i--) {
            if (sum[i] != 1) {
                return 0;
            }
        }
        return 20;
    }

    public int fullHouseScore() {
        int score = 0;
        int pairCount = 0;
        for (int i = 0; i < 6; i++) {
            if (sum[i] == 3) {
                score += (i + 1) * 3;
                pairCount++;
            } else if (sum[i] == 2) {
                score += (i + 1) * 2;
                pairCount++;
            }
        }
        if (pairCount == 2) {
            return score;
        }
        return 0;
    }

    public int chanceScore() {
        int result = 0;
        for (Die die : dice) {
            result += die.getRoll();
        }
        return result;
    }

    public int yatzyScore() {
        for (int i = 5; i >= 0; i--) {
            if (sum[i] == 5) {
                return 50;
            }
        }
        return 0;
    }
}
