package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class representing a game score. The score is the number of critters the player was able to kill.
 *
 * @author Team 6
 *
 */
public class GameScore implements Comparable<GameScore> {

    /**
     * The number of scores that are displayed in the high score window.
     */
    public static int HIGH_SCORES_COUNT = 5;

    public java.util.Date datePlayed = new Date(System.currentTimeMillis());
    public int killedCritters = 0;
    public boolean won = false;

    /**
     * Default constructor for the GameScore class.
     */
    public GameScore() {

    }

    /**
     * Constructor for the GameScore class.
     *
     * @param crittersKilled The number of critters the player has managed to kill.
     * @param won Whether or not the player won the game.
     */
    public GameScore(int crittersKilled, boolean won) {
        this.killedCritters = crittersKilled;
        this.won = won;
    }

    /**
     * Allows to sort the scores.
     */
    @Override
    public int compareTo(GameScore gameScore) {
        return gameScore.killedCritters - this.killedCritters;
    }

    /**
     * Allows to display the scores in the GameView.
     */
    @Override
    public String toString() {
        return this.datePlayed.toGMTString() + ',' + this.killedCritters + ',' + (this.won != false ? "won" : "lost");
    }

    /**
     * Allows to restore the game scores from a map file.
     *
     * @param gameScoreString The serialized version of the game score.
     */
    @SuppressWarnings("deprecation")
    public void fromString(String gameScoreString) {
        String[] infos = gameScoreString.split(",", 1000);
        this.datePlayed = new Date(Date.parse(infos[0]));
        this.killedCritters = Integer.parseInt(infos[1]);
        this.won = (infos[2].length() == 3);
    }

    /**
     * Returns the best scores from a list of scores.
     *
     * @param gameScores The complete list of scores for a game.
     * @return The highest scores associated with the game.
     */
    public static ArrayList<GameScore> getHighScores(ArrayList<GameScore> gameScores) {
        ArrayList<GameScore> response = new ArrayList<GameScore>();
        Object[] gameScoresArray = gameScores.toArray();
        Arrays.sort(gameScoresArray);
        int highScoreCounts = Math.min(GameScore.HIGH_SCORES_COUNT, gameScores.size());
        for (int i = 0; i < highScoreCounts; i++) {
            response.add((GameScore) gameScoresArray[i]);
        }
        return response;
    }

    /**
     * Returns a textual representation of the highest scores of a game.
     *
     * @param gameScores The complete list of scores for a game.
     * @return A string representing the highest scores.
     */
    public static String displayHighScores(ArrayList<GameScore> gameScores) {
        if (gameScores.size() == 0) {
            return "No high scores for this map!";
        }
        ArrayList<GameScore> highScores = GameScore.getHighScores(gameScores);
        String response = "Date                                    Critters killed  Result\n";
        for (GameScore gameScore : highScores) {
            response += gameScore.datePlayed.toGMTString() + "  ";
            response += gameScore.killedCritters + "                       ";
            if (gameScore.killedCritters < 10) {
                response += "  ";
            }
            response += (gameScore.won ? "won" : "lost") + '\n';
        }
        return response;
    }

}
