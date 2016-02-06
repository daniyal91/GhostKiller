package main;

import java.awt.EventQueue;

import controllers.MainController;

public class main {

    public static void main(final String[] args) {

        MainController gameController = new MainController();
        EventQueue.invokeLater(gameController);

    }

}
