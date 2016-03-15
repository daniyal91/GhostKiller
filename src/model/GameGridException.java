package model;

/**
 * Base class for the exceptions raised in the GameGrid class.
 *
 * @author Team 6
 *
 */
public class GameGridException extends Exception {

    public GameGridException(String string) {
        super(string);
    }

    private static final long serialVersionUID = 1L;
}