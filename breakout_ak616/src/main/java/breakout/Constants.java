package breakout;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;

import java.util.Random;

public class Constants {
    public static final String TITLE = "Breakout (Aryan Kothari)";
    public static final int SIZE = 800;
    public static final int FRAMES_PER_SECOND = 60;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
    public static final int CIRCLE_SIZE = 10;
    public static final Random DICE = new Random();
    public static final int BALL_SIZE = 10;
    public static final double MAX_ANGLE = 45;

    public static final int STARTING_LIVES = 3;
    public static final int STARTING_LEVEL = 1;
    public static final int STARTING_SCORE = 0;

    public static final int BALL_DEFAULT_POSX = SIZE/2;
    public static final int BALL_DEFAULT_POSY = SIZE-200;
    public static final int BALL_SPEED = 400;
    public static final Point2D BALL_DEFAULT_VELOCITY = new Point2D(100, 300);

    public static final int PADDLE_STARTING_POSX = SIZE/2;
    public static final int PADDLE_STARTING_POSY = SIZE-100;
    public static final int PADDLE_WIDTH = 150;
    public static final int PADDLE_HEIGHT = PADDLE_WIDTH/15;
    public static final int PADDLE_SPEED = 50;
    public static final int BRICK_DEFAULT_WIDTH = 80;
    public static final int BRICK_DEFAULT_HEIGHT = BRICK_DEFAULT_WIDTH/2;

}
