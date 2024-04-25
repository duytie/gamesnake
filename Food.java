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

    public Point getPosition() {
        return position;
    }
}

