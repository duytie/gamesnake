package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements ActionListener {
    public static final int GRID_SIZE = 20;
    private Snake snake;
    private Food food;
    private final Timer timer = new Timer(100, this::actionPerformed);
    private boolean isRunning;
    private int point;

    private GamePanelListener listener;
    private JButton exitButton;
    private JButton pointButton;

    public GamePanel() {
        setPreferredSize(new Dimension((int) (GRID_SIZE * 1.5) * 20, (int) (GRID_SIZE * 1.5) * 20));
        setBackground(new Color(105, 105 ,105));
        setFocusable(true);

        pointButton = new JButton("point");
        pointButton.setBounds(0, 0, 100, 25);
        this.add(pointButton);
        pointButton.setBackground(Color.DARK_GRAY);
        pointButton.setForeground(Color.WHITE);

        exitButton = new JButton("❌");
        exitButton.setForeground(Color.red);
        exitButton.setBounds(550, 0, 50, 25);
        this.setLayout(null);
        this.add(exitButton);
        exitButton.addActionListener(this);

        snake = new Snake();
        food = new Food();
        isRunning = false;
        point = 0;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (snake.getDirection() != Direction.DOWN)
                            snake.setDirection(Direction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        if (snake.getDirection() != Direction.UP)
                            snake.setDirection(Direction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        if (snake.getDirection() != Direction.RIGHT)
                            snake.setDirection(Direction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (snake.getDirection() != Direction.LEFT)
                            snake.setDirection(Direction.RIGHT);
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!isRunning) {
                            startGame();
                        }
                        break;

                }
            }
        });
    }

    public void startGame() {
        snake = new Snake();
        isRunning = true;
        timer.start();
        requestFocus();
    }

    private void endGame() {
        timer.stop();
        isRunning = false;
        JOptionPane.showMessageDialog(this, "Game Over! Your Point: " + point);
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            point = 0;
            updateScore();
            startGame();
        } else {
            System.exit(0);
        }
    }

    private void updateScore() {
        pointButton.setText("point: " + point);
    }

    private void increasePoint() {
        point++;
        updateScore();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw snake
        snake.draw(g);

        // Draw food
        food.draw(g);

        // Draw point
        g.setColor(Color.BLACK);
        g.drawString("point: " + point, 10, 20);
        pointButton.paint(g);
    }

    public void setGamePanelListener(GamePanelListener listener) {
        this.listener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            snake.move();

            if (snake.checkCollision(food.getPosition())) {
                snake.grow();
                food.respawn();
                increasePoint();
            }
            if (snake.checkCollision(snake.getHead())) {
                endGame();
            }
            repaint();
        }
        if (e.getSource() == exitButton) {
            if (listener != null) {
                listener.onExitSelected();
            }
        }
    }
}

