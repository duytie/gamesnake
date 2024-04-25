package game;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            SwingUtilities.invokeLater(() -> {
                GameFrame gameFrame = new GameFrame(); // Khởi tạo GameFrame

                gameFrame.setVisible(true); // Hiển thị cửa sổ trò chơi
            });
        } catch (Exception e) {
            System.err.println("An error occurred while running the game: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
