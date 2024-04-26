package game;

import java.awt.*;
import java.util.Random;
public class Food {
    private Point position;

    public Food() {
        position = new Point();
        respawn();
    }

    public void respawn() {
        Random rand = new Random();
        position.x = rand.nextInt(GamePanel.GRID_SIZE);
        position.y = rand.nextInt(GamePanel.GRID_SIZE);
    }
    public void draw(Graphics g) {
        // Vẽ hình chữ nhật đại diện cho thức ăn
        g.setColor(Color.red);
        int size = GamePanel.GRID_SIZE; // Kích thước hình tròn bằng kích thước của ô trên bảng
        int x = position.x * size ; // Đặt tọa độ x để căn giữa hình tròn trên ô
        int y = position.y * size ; // Đặt tọa độ y để căn giữa hình tròn trên ô
        g.fillOval(x, y, size , size ); // Vẽ hình tròn
    }

    public Point getPosition() {
        return position;
    }
}

