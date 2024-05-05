package game;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                GameFrame gameFrame = new GameFrame();

                gameFrame.setVisible(true);
            });
        } catch (Exception e) {
            System.err.println("An error occurred while running the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
