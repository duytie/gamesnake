package game;

import java.awt.*;
import java.util.*;

public class Snake {
    private static final int GRID_SIZE = 20;
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
            case UP:
                newHead.y--;
                break;
            case DOWN:
                newHead.y++;
                break;
            case LEFT:
                newHead.x--;
                break;
            case RIGHT:
                newHead.x++;
                break;
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
        if (point.x < 0 || point.x >= 30   || point.y < 0 || point.y >= 30) {

            return true;
        }
        return false ;
    }


  public void grow() {
        //Thêm độ dài con rắn sau khi ăn
        Point tail = body.get(body.size() - 1);
        body.add(new Point(tail));
    }

   public void draw(Graphics g) {
        // hinh danh rắn
        g.setColor(Color.WHITE);
        g.fillRect(body.get(0).x * GRID_SIZE, body.get(0).y * GRID_SIZE, GRID_SIZE, GRID_SIZE);

        // Màu của các phần tử còn lại trong con rắn
        g.setColor(new Color(0,255,255));
        for (int i = 1; i < body.size(); i++) {
            g.fillRect(body.get(i).x * GRID_SIZE, body.get(i).y * GRID_SIZE, GRID_SIZE, GRID_SIZE);
        }

    }

    public ArrayList<Point> getBody() {
        return body;
    }

    public Point getHead() {
        return body.get(0);
    }

    public Direction getDirection() {return direction;}

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isMoving() {return false;}


}
