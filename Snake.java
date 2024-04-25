package game;

import java.awt.*;
import java.util.*;

public class Snake {
    private final ArrayList<Point> body;
    private Direction direction;

    public Snake() {
        body = new ArrayList<>();
        body.add(new Point(10, 10));
        body.add(new Point(9, 10));
        body.add(new Point(8, 10));
        direction = Direction.RIGHT;
    }

    public void move() {
        Point newHead = new Point(body.get(0));
        switch (direction) {
            case UP -> newHead.y--;
            case DOWN -> newHead.y++;
            case LEFT -> newHead.x--;
            case RIGHT -> newHead.x++;
        }

        body.add(0, newHead);
        body.remove(body.size() - 1);
    }

    public boolean checkCollision(Point point) {
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(point)) {
                return true;
            }
        }
        // Kiểm tra va chạm với biên của màn hình
        return point.x < 0 || point.x >= GamePanel.GRID_SIZE || point.y < 0 || point.y >= GamePanel.GRID_SIZE;
    }

    public void grow() {
        //Thêm độ dài con rắn sau khi ăn
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail));
    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.get(0);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
