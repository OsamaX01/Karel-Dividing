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

    public void moveOneStepTo(Direction direction) {
        switch (direction) {
            case RIGHT:
                setX(getX() + 1);
                break;
            case LEFT:
                setX(getX() - 1);
                break;
            case UP:
                setY(getY() + 1);
                break;
            case DOWN:
                setY(getY() - 1);
                break;
        }
    }
}

public class Homework extends SuperKarel {
    private Direction faceDirection;
    private int gridHeight;
    private int gridWidth;
    private Point karelPosition;

    private void initiate() {
        setBeepersInBag(10000);
        karelPosition = new Point(1, 1);
        moveTo(karelPosition, false);
        faceDirection = Direction.RIGHT;
    }

    public Homework() {
        initiate();
    }

    public void putBeeper() {
        if (!beepersPresent()) {
            super.putBeeper();
        }
    }

    private void turnFaceDirectionTo(Direction direction) {
        while (faceDirection != direction) {
            turnLeft();
            int nextDirection = (faceDirection.ordinal() + 1) % 4;
            faceDirection = Direction.values()[nextDirection];
        }
    }

    private void moveTo(Direction direction) {
        turnFaceDirectionTo(direction);
        move();
        karelPosition.moveOneStepTo(direction);
    }

    private void moveTo(Point destination, boolean putBeepersOnTheWay) {
        while (karelPosition.getX() != destination.getX()) {
            if (putBeepersOnTheWay) {
                putBeeper();
            }
            if (destination.getX() < karelPosition.getX()) {
                moveTo(Direction.LEFT);
            } else {
                moveTo(Direction.RIGHT);
            }
        }
        if (putBeepersOnTheWay) {
            putBeeper();
        }

        while (karelPosition.getY() != destination.getY()) {
            if (putBeepersOnTheWay) {
                putBeeper();
            }
            if (destination.getY() < karelPosition.getY()) {
                moveTo(Direction.DOWN);
            } else {
                moveTo(Direction.UP);
            }
        }
        if (putBeepersOnTheWay) {
            putBeeper();
        }
    }

    private void getGridDimensions() {
        turnFaceDirectionTo(Direction.UP);
        while (frontIsClear()) {
            moveTo(Direction.UP);
        }
        turnFaceDirectionTo(Direction.RIGHT);
        while (frontIsClear()) {
            moveTo(Direction.RIGHT);
        }
        gridWidth = karelPosition.getX();
        gridHeight = karelPosition.getY();
    }

    private void zigzagMove(Direction mainDirection, Direction swappingDirection) {
        turnFaceDirectionTo(mainDirection);
        boolean turn = true;
        while (frontIsClear()) {
            putBeeper();
            if (turn) {
                moveTo(swappingDirection);
            } else {
                Direction oppositeDirection = Direction.values()[(swappingDirection.ordinal() + 2) % 4];
                moveTo(oppositeDirection);
            }
            putBeeper();
            moveTo(mainDirection);
            turn = !turn;
        }
        putBeeper();
        if (turn) {
            moveTo(swappingDirection);
        } else {
            Direction oppositeDirection = Direction.values()[(swappingDirection.ordinal() + 2) % 4];
            moveTo(oppositeDirection);
        }
        putBeeper();
    }

    private void divideVertically() {
        if (gridWidth < 3) {
            return;
        }

        moveTo(new Point(gridWidth / 2 + 1, gridHeight), false);
        if (gridWidth % 2 != 0) {
            moveTo(new Point(gridWidth / 2 + 1, 1), true);
        } else {
            zigzagMove(Direction.DOWN, Direction.LEFT);
        }
    }

    private void divideHorizontally() {
        if (gridHeight < 3) {
            return;
        }

        if (gridHeight % 2 != 0) {
            moveTo(new Point(gridWidth, gridHeight / 2 + 1), false);
            moveTo(new Point(1, gridHeight / 2 + 1), true);
        } else {
            moveTo(new Point(gridWidth, gridHeight / 2), false);
            zigzagMove(Direction.LEFT, Direction.UP);
        }
    }

    public void run() {
        initiate();
        getGridDimensions();
        divideVertically();
        divideHorizontally();
    }
}