package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

import model.GameScore;

public class GameScoreTestCase {

    @Test
    public void testGetHighScores() throws IOException {
        ArrayList<GameScore> gameScores = new ArrayList<GameScore>();
        gameScores.add(new GameScore(50, true));
        gameScores.add(new GameScore(0, false));
        gameScores.add(new GameScore(10, false));
        gameScores.add(new GameScore(4, false));
        gameScores.add(new GameScore(0, false));
        gameScores.add(new GameScore(45, false));
        gameScores.add(new GameScore(47, false));

        ArrayList<GameScore> highScores = GameScore.getHighScores(gameScores);

        assertEquals(highScores.size(), GameScore.HIGH_SCORES_COUNT);
        assertEquals(highScores.get(0).killedCritters, 50);
        assertEquals(highScores.get(4).killedCritters, 4);
    }

    @Test
    public void testGetFewHighScores() throws IOException {
        ArrayList<GameScore> gameScores = new ArrayList<GameScore>();
        for (int i = 0; i < GameScore.HIGH_SCORES_COUNT - 1; i++) {
            gameScores.add(new GameScore(50, true));
        }

        ArrayList<GameScore> highScores = GameScore.getHighScores(gameScores);

        assertEquals(highScores.size(), GameScore.HIGH_SCORES_COUNT - 1);
    }

    @Test
    public void testSerializationWon() throws IOException {

        GameScore gameScore = new GameScore(35, true);

        GameScore serialized = new GameScore();
        System.out.println(gameScore.toString());
        serialized.fromString(gameScore.toString());

        assertTrue(serialized.won);
        assertEquals(serialized.killedCritters, 35);

    }

    @Test
    public void testSerializationLost() throws IOException {

        GameScore gameScore = new GameScore(0, false);

        GameScore serialized = new GameScore();
        serialized.fromString(gameScore.toString());

        assertFalse(serialized.won);
        assertEquals(serialized.killedCritters, 0);

    }

    @Test
    public void testNoHighScores() throws IOException {

        String highScore = GameScore.displayHighScores(new ArrayList<GameScore>());
        assertEquals(highScore, "No high scores for this map!");

    }


}