package game;

import util.*;
import util.Displayable;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.LinkedList;
import java.util.Random;

public class Snake extends Displayable {
    private int snakeSize = 20;
    private int maxX = Options.SCREEN_WIDTH / snakeSize;
    private int maxY = Options.SCREEN_HEIGHT / snakeSize;
    private Direction currentDirection = Direction.RIGHT;
    private Direction lastMove = Direction.RIGHT;
    private Color snakeColor = Globals.niceBlue;
    private Color foodColor = Color.YELLOW;
    private int snakeRow = Options.SCREEN_HEIGHT / snakeSize;
    private int snakeCol = Options.SCREEN_WIDTH / snakeSize;

    private boolean foodSpawned = false;
    private VectorSnake foodLocation = null;

    private int grow = 3;
    private int score = 0 - grow;
    private LinkedList<VectorSnake> snake = new LinkedList();

    private double lastMovedDt = 0;
    // adjusting this sets the time between updates/moves for snake, in seconds?
    private double howFast = .2;
    private boolean goodGame = true;

    public Snake() {
        spawnSnake();
        //TODO not closing properly

        // difficulty settings? (adjusts speed)
        // set snake size in settings (adjusts how big window is)
    }

    private void spawnSnake() {
        VectorSnake newSegment = new VectorSnake(1, 1);
        snake.addFirst(newSegment);
    }

    @Override
    public void update(double dt) {
        if (Globals.kl.isKeyReleased(KeyEvent.VK_ESCAPE)) {
            if (!goodGame) {
                System.out.println("YOU LOSE, score: " + score);
                Globals.ph.switchScene(Scene.MAIN);
            } else {
                Globals.ph.pauseScene(this);
            }
        }

        if (Globals.kl.isKeyPressed(KeyEvent.VK_UP)) {
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

        if (Globals.kl.isKeyPressed(KeyEvent.VK_SHIFT)) {
            howFast = .1;
        } else {
            howFast = .2;
        }

        lastMovedDt += dt;
        if (lastMovedDt >= howFast && goodGame) {
            lastMovedDt = 0;
            // set this as the new head
            snake.addFirst(getNewSnakeHead());
            // remove tail or grow
            if (grow > 0) {
                score++;
                grow--;
            } else {
                if (!foodSpawned) {
                    makeFood();
                }
                snake.removeLast();
            }
        }
    }

    private void makeFood() {
        // generate a random x and a random y between 0 and maxX/maxY
        while (!foodSpawned) {
            Random rand = new Random();
            VectorSnake trialFood = new VectorSnake(
                    rand.nextInt(0,maxX),
                    rand.nextInt(0,maxY));
            boolean noImpact = true;
            for (int i = 0; i < snake.size(); i++) {
                if ((snake.get(i).x == trialFood.x) &&
                        (snake.get(i).y == trialFood.y)) {
                    noImpact = false;
                }
            }
            if (noImpact) {
                foodLocation = trialFood;
                foodSpawned = true;
                System.out.println("FOOD X: " + trialFood.x + " FOOD Y: " + trialFood.y);
            }
        }

    }

    private VectorSnake getNewSnakeHead() {
        int curHeadX = snake.getFirst().x;
        int curHeadY = snake.getFirst().y;

        VectorSnake newHead;
        if (currentDirection == Direction.RIGHT) {
            if (lastMove != Direction.LEFT) {
                newHead = new VectorSnake(curHeadX + 1, curHeadY);
                lastMove = Direction.RIGHT;
            } else {
                newHead = new VectorSnake(curHeadX - 1, curHeadY);
            }
        } else if (currentDirection == Direction.LEFT) {
            if (lastMove != Direction.RIGHT) {
                newHead = new VectorSnake(curHeadX - 1, curHeadY);
                lastMove = Direction.LEFT;
            } else {
                newHead = new VectorSnake(curHeadX + 1, curHeadY);
            }
        } else if (currentDirection == Direction.UP) {
            if (lastMove != Direction.DOWN) {
                newHead = new VectorSnake(curHeadX, curHeadY - 1);
                lastMove = Direction.UP;
            } else {
                newHead = new VectorSnake(curHeadX, curHeadY + 1);
            }
        } else { // it's going down.
            if (lastMove != Direction.UP) {
                newHead = new VectorSnake(curHeadX, curHeadY + 1);
                lastMove = Direction.DOWN;
            } else {
                newHead = new VectorSnake(curHeadX, curHeadY - 1);
            }
        }
        collisionDetection(newHead);
        return newHead;
    }

    private void collisionDetection(VectorSnake movingSegment) {
        // compares max and min "x and y" values to crash into walls
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

        // test newest segment to see if it eats the food:

        if (foodLocation != null) {
            if ((foodLocation.x == movingSegment.x) &&
                    (foodLocation.y == movingSegment.y)) {
                foodSpawned = false;
                foodLocation = null;
                grow++;
            }
        }
    }

    private void gameOver() {
        goodGame = false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(snakeColor);
        for (int i = 0; i < snake.size(); i++) {
            g.fillRect((snakeSize * snake.get(i).x) + 1, (snakeSize * snake.get(i).y) + 1,
                    snakeSize - 2, snakeSize - 2);
        }
        if (foodLocation != null) {
            g.setColor(Color.ORANGE);
            g.fillRect((snakeSize * foodLocation.x) + 1, (snakeSize * foodLocation.y) + 1,
                    snakeSize - 2, snakeSize - 2);
        }

        if (!goodGame) {
            FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(Globals.font);
            int x = (Options.SCREEN_WIDTH - fontMetrics.stringWidth("YOU DIED")) / 2;
            int y = (Options.SCREEN_HEIGHT + fontMetrics.getAscent()) / 2;

            g.setColor(Color.RED);
            g.setFont(Globals.font);
            g.drawString("YOU DIED", x, y);
            g.drawString("" + score , x, y + 40);

        }
    }
}
