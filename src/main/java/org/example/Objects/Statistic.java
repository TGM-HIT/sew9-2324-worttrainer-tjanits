package org.example.Objects;

/**
 * Saves the progress and achievements of a player-session
 * Can return the amount of right and wrong answers as well as statistics
 * like the average accuracy of the answers provided
 * @author Tobias Janits
 * @version 09-10-2023 | dd-mm-yyyy
 */
public class Statistic {
    private int right;
    private int wrong;

    /**
     * empty constructor for normal use
     */
    public Statistic() {
        this.right = 0;
        this.wrong = 0;
    }

    /**
     * constructor for when the counter doesn't start at 0
     * @param right the amount of correct answers
     * @param wrong the amount of wrong answers
     */
    public Statistic(int right, int wrong) {
        this.right = right;
        this.wrong = wrong;
    }

    /**
     * increases the counter for correct answers by 1
     */
    public void addCorrectAnswer() {
        this.right ++;
    }

    /**
     * increases the counter for wrong answers by 1
     */
    public void addWrongAnswer() {
        this.wrong ++;
    }

    /**
     * @return returns the amount of correct answers
     */
    public int getRight() {
        return right;
    }

    /**
     * @return returns the amount of wrong answers
     */
    public int getWrong() {
        return wrong;
    }

    /**
     * @return returns the amount of rounds played
     */
    public int getRoundsPlayed() {
        return this.right + this.wrong;
    }

    public double average() {
        return (this.right - this.wrong) / (this.right + this.wrong);
    }

    /**
     * Builds a String with all the basic information contained
     * @return returns a String containing: Correct-, Wrong- and Average Scores
     */
    public String retreiveStatsString() {
        StringBuilder strB = new StringBuilder();
        strB.append("Correct answers: " + this.right);
        strB.append("\n");
        strB.append("Wrong answers: " + this.wrong);
        strB.append("\n");
        strB.append("Average Score: " + this.average());
        return strB.toString();
    }
}
