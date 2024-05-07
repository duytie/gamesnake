package game;

import javax.swing.*;
import java.awt.*;
public class GameFrame extends JFrame implements StartAppListener,GamePanelListener, HistoryListener {
    private GamePanel gamePanel;
    private JPanel contentPane;

    public GameFrame() {
        setTitle("Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        gamePanel = new GamePanel();

        contentPane = new JPanel(new BorderLayout());
        StartAppPanel startApp = new StartAppPanel();
        contentPane.add(startApp, BorderLayout.CENTER);
        contentPane.setPreferredSize(new Dimension(600, 600));

        setContentPane(contentPane);

        pack();
        setLocationRelativeTo(null);

        startApp.setStartAppListener(this);
        gamePanel.setGamePanelListener(this);

    }

    @Override
    public void onTroChoiMoiClicked() {
        System.out.println("Trò chơi mới đã được click");
        contentPane.removeAll();
        contentPane.add(gamePanel, BorderLayout.CENTER);
        gamePanel.requestFocusInWindow();
        contentPane.validate();
        contentPane.repaint();

    }

    @Override
    public void onLichSuClicked() {
        System.out.println("Lịch sử đã được click");
        contentPane.removeAll();
        HistoryPanel history = new HistoryPanel();
        contentPane.add(history, BorderLayout.CENTER);
        history.setHistorylListener(this);
        history.requestFocusInWindow();
        contentPane.validate();
        contentPane.repaint();
    }


    @Override
    public void onThoatClicked() {
        System.out.println("Thoát đã được click");
        System.exit(0);
    }
    @Override
    public void onExitSelected() {
        System.out.println("Exit từ GamePanel đã được click");
        contentPane.removeAll();
        StartAppPanel startApp = new StartAppPanel();
        startApp.setStartAppListener(this);
        contentPane.add(startApp,BorderLayout.CENTER);
        gamePanel.requestFocusInWindow();
        contentPane.validate();
        contentPane.repaint();

    }
    @Override
    public void onSelectExitFromHistory() {
        System.out.println("Exit từ History đã được click");
        contentPane.removeAll();
        StartAppPanel startApp = new StartAppPanel();
        startApp.setStartAppListener(this);
        contentPane.add(startApp,BorderLayout.CENTER);
        gamePanel.requestFocusInWindow();
        contentPane.validate();
        contentPane.repaint();


    }
    @Override
    public void onGameEnd() {
        System.out.println("Trò chơi đã kết thúc, chuyển về màn hình StartApp");
        contentPane.removeAll();
        StartAppPanel startApp = new StartAppPanel();
        startApp.setStartAppListener(this);
        contentPane.add(startApp, BorderLayout.CENTER);
        contentPane.validate();
        contentPane.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameFrame::new);
    }


}

