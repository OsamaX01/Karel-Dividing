import stanford.karel.Karel;
import stanford.karel.KarelProgram;
import stanford.karel.SuperKarel;

enum Direction {RIGHT, UP, LEFT, DOWN};

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
    private Direction faceDirection;
    private int gridHeight;
    private int gridWidth;
    private Point karelPosition;

    private void initiate() {
        faceDirection = Direction.RIGHT;
        karelPosition = new Point(1, 1);
    }

    public Homework() {
        initiate();
    }

    private void turnFaceDirectionTo(Direction direction) {
        while (faceDirection != direction) {
            turnLeft();
            int nextDirection = (faceDirection.ordinal() + 1) % 4;
            faceDirection = Direction.values()[nextDirection];
        }
    }

    private void moveRight() {
        turnFaceDirectionTo(Direction.RIGHT);
        move();
        karelPosition.setX(karelPosition.getX() + 1);
    }

    private void moveLeft() {
        turnFaceDirectionTo(Direction.LEFT);
        move();
        karelPosition.setX(karelPosition.getX() - 1);
    }

    private void moveUp() {
        turnFaceDirectionTo(Direction.UP);
        move();
        karelPosition.setY(karelPosition.getY() + 1);
    }

    private void moveDown() {
        turnFaceDirectionTo(Direction.DOWN);
        move();
        karelPosition.setY(karelPosition.getY() - 1);
    }

    private void getGridDimensions() {
        turnFaceDirectionTo(Direction.UP);
        while (frontIsClear()) {
            moveUp();
        }
        turnFaceDirectionTo(Direction.RIGHT);
        while (frontIsClear()) {
            moveRight();
        }
        gridWidth = karelPosition.getX();
        gridHeight = karelPosition.getY();
    }

    private void moveTo(Point destination, boolean putBeepersOnTheWay) {
        while (karelPosition.getX() != destination.getX()) {
            if (putBeepersOnTheWay && !beepersPresent()) {
                putBeeper();
            }
            if (destination.getX() < karelPosition.getX()) {
                moveLeft();
            } else {
                moveRight();
            }
        }
        if (putBeepersOnTheWay && !beepersPresent()) {
            putBeeper();
        }

        while (karelPosition.getY() != destination.getY()) {
            if (putBeepersOnTheWay && !beepersPresent()) {
                putBeeper();
            }
            if (destination.getY() < karelPosition.getY()) {
                moveDown();
            } else {
                moveUp();
            }
        }
        if (putBeepersOnTheWay && !beepersPresent()) {
            putBeeper();
        }
    }

    public void run() {
        initiate();
        getGridDimensions();
    }
}