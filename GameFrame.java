package game;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    public GameFrame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        MenuPanel menuPanel = new MenuPanel(gamePanel);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.add(menuPanel, BorderLayout.WEST);
        contentPane.add(gamePanel, BorderLayout.CENTER);

        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }
}
