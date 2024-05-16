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
    private Timer timer;
    private boolean isRunning;
    private int point;
    private int speed;

    private GamePanelListener listener;
    private JButton exitButton;
    private JButton pointButton;

    private final DataBase dataBase; // Thêm đối tượng DataBase

    public GamePanel() {
        setPreferredSize(new Dimension(600, 600));
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
        speed = 100; // Khởi tạo tốc độ ban đầu của rắn

        timer = new Timer(speed, this::actionPerformed); // Khởi tạo timer với tốc độ ban đầu

        dataBase = new DataBase(); // Khởi tạo đối tượng DataBase

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
                        case KeyEvent.VK_SPACE:
                            if (!isRunning) {
                                startGame();
                            }
                    }

                }
            }

        });
    }

    public void startGame() {
        snake = new Snake();
        snake.setDirection(Direction.RIGHT);
        isRunning = true;
        point = 0; // Đặt lại điểm số về 0
        speed = 100; // Đặt lại tốc độ về giá trị ban đầu
        timer.setDelay(speed); // Đặt lại tốc độ của timer
        updateScore();
        timer.start();
        requestFocus();
    }

    private void endGame() {
        timer.stop();
        isRunning = false;
        Point snakeHead = snake.getHead();
        System.out.println("Snake head coordinates: (" + snakeHead.x + ", " + snakeHead.y + ")");
        JOptionPane.showMessageDialog(this, "Game Over! Your Point: " + point);
        int option = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Game Over", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            point = 0;
            updateScore();
            startGame();
        } else {
            // Lưu điểm số vào cơ sở dữ liệu khi người chơi kết thúc trò chơi
            dataBase.addDataToDB(point); // Cập nhật điểm vào cơ sở dữ liệu
            if (listener != null) listener.onGameEnd();

        }
    }

    private void updateScore() {
        pointButton.setText("point: " + point);
    }

    private void increasePoint() {
        point++;
        updateScore();
        if (point % 100 == 0) {
            speed -= 100; // Tăng tốc độ (giảm độ trễ của timer)
            timer.setDelay(Math.max(speed, 50)); // Đảm bảo tốc độ không giảm xuống dưới 50ms
        }
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
                return ;
            }
            repaint();
        }
        if (e.getSource() == exitButton) {
            timer.stop();
            isRunning = false ;
            if (listener != null) {
                listener.onExitSelected();
            }
            return;
        }

    }
}
