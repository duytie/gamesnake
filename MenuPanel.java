package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MenuPanel extends JPanel {
    private GamePanel gamePanel;

    public MenuPanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(new BorderLayout()); // Sử dụng BorderLayout

        JPanel buttonPanel = new JPanel(new GridLayout(4, 1)); // Tạo panel để chứa các nút
        JButton startButton = new JButton("Start");
        JButton historyButton = new JButton("History");
        JButton guideButton = new JButton("Guide");
        JButton exitButton = new JButton("Exit");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.startGame();
                gamePanel.requestFocus();
            }
        });

        guideButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MenuPanel.this, "Instructions:\n- Use arrow keys to control the snake\n- Eat the red food to grow\n- Avoid hitting walls or yourself");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(MenuPanel.this, "Do you want to exit the game?", "Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Thêm các nút vào panel
        buttonPanel.add(startButton);
        buttonPanel.add(historyButton);
        buttonPanel.add(guideButton);
        buttonPanel.add(exitButton);

        // Thêm panel chứa các nút vào vị trí WEST của menuPanel
        add(buttonPanel, BorderLayout.WEST);
    }
}
