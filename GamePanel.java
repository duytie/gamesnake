package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    public static final int GRID_SIZE = 20;
    private Snake snake;
    private Food food;
    private final Timer timer = new Timer(100, this::actionPerformed);
    private boolean isRunning;
    private int point;

    public GamePanel() {
        setPreferredSize(new Dimension((int) (GRID_SIZE * 1.5) * 20, (int) (GRID_SIZE * 1.5) * 20));
        setBackground(Color.black);
        setFocusable(true);

        snake = new Snake();
        food = new Food();
        isRunning = false;
        point = 0;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!snake.isMoving()) {
                    int keyCode = e.getKeyCode();
                    switch (keyCode) {
                        case KeyEvent.VK_UP:
                            if (snake.getDirection() != Direction.DOWN) {
                                snake.setDirection(Direction.UP);
                            }
                            break;
                        case KeyEvent.VK_DOWN:
                            if (snake.getDirection() != Direction.UP) {
                                snake.setDirection(Direction.DOWN);
                            }
                            break;
                        case KeyEvent.VK_LEFT:
                            if (snake.getDirection() != Direction.RIGHT) {
                                snake.setDirection(Direction.LEFT);
                            }
                            break;
                        case KeyEvent.VK_RIGHT:
                            if (snake.getDirection() != Direction.LEFT) {
                                snake.setDirection(Direction.RIGHT);
                            }
                            break;
                    }
                    snake.setMoving(true);
                }
            }
        });
    }

    public void startGame() {
        snake = new Snake();
        food = new Food();
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
            point =0;
            startGame();
        } else {
            System.exit(0);
        }
    }

    private void increasePoint() {
        point++;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int row = 0; row < getHeight() / GRID_SIZE; row++) {
            for (int col = 0; col < getWidth() / GRID_SIZE; col++) {
                // Tính toán màu cho ô hiện tại
                Color color = (row + col) % 2 == 0 ? Color.white : Color.lightGray;

                // Đặt màu cho ô hiện tại
                g.setColor(color);

                // Vẽ ô hiện tại
                g.fillRect(col * GRID_SIZE, row * GRID_SIZE, GRID_SIZE, GRID_SIZE);
            }
        }
        // Draw snake
        snake.draw(g);

        // Draw food
      food.draw(g);

        // poin
        g.setColor(Color.BLACK);
        g.drawString("point: " + point, 10, 20);
    }

    private void actionPerformed(ActionEvent e) {
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
    }
}
