import stanford.karel.SuperKarel;

class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}

public class Homework extends SuperKarel {
    private int faceDirection;
    private int gridHeight;
    private int gridWidth;
    private Point karelPosition;

    public Homework() {
        faceDirection = 0;
        karelPosition = new Point(1, 1);
    }

    private void turnFaceDirectionTo(int direction) {
        while (faceDirection != direction) {
            turnRight();
            faceDirection = (faceDirection + 1) % 4;
        }
    }

    private void moveRight() {
        turnFaceDirectionTo(0);
        move();
    }

    private void moveLeft() {
        turnFaceDirectionTo(2);
        move();
    }

    private void moveUp() {
        turnFaceDirectionTo(1);
        move();
    }

    private void moveDown() {
        turnFaceDirectionTo(3);
        move();
    }

    public void run() {
        move();
    }
}
