package game;

import util.*;
import util.Displayable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Game extends Displayable {
    private Direction currentDirection = Direction.RIGHT;
    private Direction lastMove = Direction.RIGHT;
    private int snakeSize = 40;
    private Color snakeColor = Globals.niceBlue;
    private Color foodColor = Color.YELLOW;
    private int snakeRow = Options.SCREEN_HEIGHT / snakeSize;
    private int snakeCol = Options.SCREEN_WIDTH / snakeSize;

    private int grow = 20;

    private int score = 0 - grow;
    private LinkedList<SnakeSegment> snake = new LinkedList();

    private double lastMovedDt = 0;
    // adjusting this sets the time between updates/moves for snake, in seconds?
    private double howFast = .5;

    public Game() {
        spawnSnake();

        // collision detection - with snake and border (wraparound setting?)
        // food generation

        // scoring? (hiscores/names)
        // speed button like shift? (settings?) (maybe adds to score?)
        // difficulty settings? (adjusts speed)
        // set snake size in settings (adjusts how big window is)
    }

    private void spawnSnake() {
        SnakeSegment newSegment = new SnakeSegment(1, 1);
        snake.addFirst(newSegment);
    }

    @Override
    public void update(double dt) {
        if (Globals.kl.isKeyReleased(KeyEvent.VK_ESCAPE)) {
            Globals.ph.pauseScene(this);
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_UP)) {
            if (currentDirection != Direction.DOWN) {
                currentDirection = Direction.UP;
            }
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_DOWN)) {
            if (currentDirection != Direction.UP) {
                currentDirection = Direction.DOWN;
            }

        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_LEFT)) {
            if (currentDirection != Direction.RIGHT) {
                currentDirection = Direction.LEFT;
            }
        } else if (Globals.kl.isKeyPressed(KeyEvent.VK_RIGHT)) {
            if (currentDirection != Direction.LEFT) {
                currentDirection = Direction.RIGHT;
            }
        }
        lastMovedDt += dt;
        if (lastMovedDt >= howFast) {
            lastMovedDt = 0;
            // set this as the new head
            snake.addFirst(getNewSnakeHead());
            // remove tail or grow
            if (grow > 0) {
                score++;
                grow--;
            } else {
                snake.removeLast();
            }
        }
    }

    private SnakeSegment getNewSnakeHead() {
        int curHeadX = snake.getFirst().x;
        int curHeadY = snake.getFirst().y;

        SnakeSegment newHead;
        if (currentDirection == Direction.RIGHT) {
            if (lastMove != Direction.LEFT) {
                newHead = new SnakeSegment(curHeadX + 1, curHeadY);
                lastMove = Direction.RIGHT;
            } else {
                newHead = new SnakeSegment(curHeadX - 1, curHeadY);
            }
        } else if (currentDirection == Direction.LEFT) {
            if (lastMove != Direction.RIGHT) {
                newHead = new SnakeSegment(curHeadX - 1, curHeadY);
                lastMove = Direction.LEFT;
            } else {
                newHead = new SnakeSegment(curHeadX + 1, curHeadY);
            }
        } else if (currentDirection == Direction.UP) {
            if (lastMove != Direction.DOWN) {
                newHead = new SnakeSegment(curHeadX, curHeadY - 1);
                lastMove = Direction.UP;
            } else {
                newHead = new SnakeSegment(curHeadX, curHeadY + 1);
            }
        } else { // it's going down.
            if (lastMove != Direction.UP) {
                newHead = new SnakeSegment(curHeadX, curHeadY + 1);
                lastMove = Direction.DOWN;
            } else {
                newHead = new SnakeSegment(curHeadX, curHeadY - 1);
            }
        }
        collisionDetection(newHead);
        return newHead;
    }

    private void collisionDetection(SnakeSegment movingSegment) {
        // figuring out max "x" value to crash into right wall
        int maxX = Options.SCREEN_WIDTH / snakeSize;
        // figuring out max "y" value to crash into bottom wall
        int maxY = Options.SCREEN_HEIGHT / snakeSize;
        // test newest segment to see if it hit the walls:
        if ((movingSegment.x > maxX) || (movingSegment.x < 0) ||
            (movingSegment.y > maxY) || (movingSegment.y < 0)) {
            gameOver();
        }

        // test newest segment to see if it hit the snake:
        for (int i = 0; i < snake.size(); i++) {
            if ((snake.get(i).x == movingSegment.x) &&
                (snake.get(i).y == movingSegment.y)) {
                gameOver();
            }
        }
    }

    private void gameOver() {
        System.out.println("YOU LOSE, score: " + score);
        Globals.ph.switchScene(Scene.MAIN);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(snakeColor);
        for (int i = 0; i < snake.size(); i++) {
            g.fillRect((snakeSize * snake.get(i).x) + 1, (snakeSize * snake.get(i).y) + 1,
                    snakeSize - 2, snakeSize - 2);
        }
    }
}
